package com.example.javagarden.controllers;

import com.example.javagarden.data.GardenRepository;

import com.example.javagarden.models.Bed;
import com.example.javagarden.models.Garden;

import com.example.javagarden.service.DeleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("garden")
public class GardenController {



    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping
    public String displayGarden( Model model) {

                model.addAttribute("title", "Gardens");
                model.addAttribute("gardens", gardenRepository.findAll());

        return "garden/index";
    }

    @GetMapping("create")
    public String displayCreateGardenForm(Model model) {
        model.addAttribute("title", "Create Garden");
        model.addAttribute(new Garden());
//        model.addAttribute("plantTimes", plantTimeRepository.findAll());
        model.addAttribute("gardenBedNumber", 1);
        return "garden/create";
    }


    @PostMapping("create")
    public String processCreateGardenForm(@Valid @ModelAttribute Garden garden, @RequestParam(value = "gardenBedNumber", defaultValue = "1") int gardenBedNumber,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Garden");
            model.addAttribute(new Garden());
            return "garden/create";
        }

        List<Bed> beds = new ArrayList<>();

        for(int i = 0; i < gardenBedNumber; i++){
                Bed bed = new Bed();
                bed.setName("new bed " + (i+1));
                beds.add(bed);
            }

        garden.setBeds(beds);

        gardenRepository.save(garden);

        return "redirect:../bed/create?gardenId=" + garden.getId();
    }


    @GetMapping("delete")
    public String displayDeleteGardenForm(Model model) {
        model.addAttribute("title", "Delete Gardens");
        model.addAttribute("gardens", gardenRepository.findAll());
        return "garden/delete";
    }

    @PostMapping("delete")
    public String processDeleteGardenForm(@RequestParam(required = false) int[] gardenIds) {

        DeleteService.deleteData( gardenIds, gardenRepository);

        return "redirect:../garden";

    }
//
//    @GetMapping("detail")
//    public String displayEventDetails(@RequestParam Integer plantId, Model model) {
//
//        Optional<Plant> result = plantRepository.findById(plantId);
//
//        if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Event ID: " + plantId);
//        } else {
//            Plant plant = result.get();
//            model.addAttribute("title", plant.getName() + " Details");
//            model.addAttribute("plant", plant);
//        }
//
//        return "plant/detail";
//    }

}
