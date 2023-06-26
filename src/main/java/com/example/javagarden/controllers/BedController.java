package com.example.javagarden.controllers;

import com.example.javagarden.data.BedRepository;
import com.example.javagarden.data.GardenRepository;

import com.example.javagarden.data.PlantingRepository;
import com.example.javagarden.models.*;

import com.example.javagarden.service.UserGardenDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("bed")
public class BedController {


    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private PlantingRepository plantingRepository;

    @Autowired
    private UserGardenDataService userGardenDataService;


@GetMapping("create")
public String displayCreateBedForm(@RequestParam(value = "bedNumber", defaultValue = "1") int bedNumber, @RequestParam("gardenId") int gardenId, Model model) {

    Optional<Garden> result = gardenRepository.findById(gardenId);

    if (result.isEmpty()) {
        model.addAttribute("title", "Invalid Garden ID: " + gardenId);
    } else {

    }
        Garden garden = result.get();

        String gardenName = garden.getName();


    model.addAttribute("title", "Create Beds for " + gardenName);



    return "bed/create";
}


    @GetMapping("delete")
    public String deleteBed(@RequestParam int gardenId, @RequestParam int bedId, HttpServletRequest request){

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        Boolean cleared = false;

        for(Garden garden : userGardenData.getGardens()){
            if (garden.getId() == gardenId) {
                cleared = true;
            }
        }



        Optional<Bed> result = bedRepository.findById(bedId);

        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Plant ID: " + plantId);
        } else {

            Bed bed = result.get();


            if (bed.getGarden().getId() == gardenId && cleared)
            {
                List<Plot> plots = bed.getPlots();
//
                for(Plot plot: plots){
                    if(plot.getPlanting() != null) {
                        Planting planting = plot.getPlanting();
                        plot.removePlanting(planting);
                        plantingRepository.delete(planting);
                    }
                }


                bedRepository.deleteById(bedId);
            }
        }

        return "redirect:../garden/detail?gardenId=" + gardenId;
    }

}


