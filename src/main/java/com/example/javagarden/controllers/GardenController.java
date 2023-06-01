package com.example.javagarden.controllers;

import com.example.javagarden.data.BedRepository;
import com.example.javagarden.data.GardenRepository;

import com.example.javagarden.controllers.dto.GardenStartDTO;
import com.example.javagarden.models.Bed;
import com.example.javagarden.models.Garden;

import com.example.javagarden.service.DeleteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("garden")
public class GardenController {


    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private BedRepository bedRepository;

    @GetMapping
    public String displayGarden(Model model) {
        model.addAttribute("title", "Gardens");
        model.addAttribute("gardens", gardenRepository.findAll());
        return "garden/index";
    }




    @GetMapping("start")
    public String displayStartGardenForm(Model model) {

        GardenStartDTO gardenStartDTO = new GardenStartDTO();
        gardenStartDTO.setBedNum(1);

        model.addAttribute("title", "Start Garden");
        model.addAttribute("gardenStartDTO", gardenStartDTO);

        return "garden/start";
    }

    @PostMapping("start")
    public String processStartGardenForm(@ModelAttribute @Valid GardenStartDTO gardenStartDTO, Errors errors, Model model){


        if (errors.hasErrors()) {
            model.addAttribute("title", "Start Garden");
            return "garden/start";
        }

        int bedNum = gardenStartDTO.getBedNum();
        String name = gardenStartDTO.getName();

    return"redirect:../garden/create?bedNum="+ bedNum +"&name="+ name;
}

    @GetMapping("create")
    public String displayCreateGardenForm(@RequestParam(value = "bedNum") int bedNum, @RequestParam("name") String name, Model model) {


        Garden garden = new Garden(name);


        model.addAttribute("title", "Create Garden");
        model.addAttribute("garden", garden);
        model.addAttribute("bedNum", bedNum);

        return "garden/create";
    }

    @PostMapping("create")
    public String processCreateGardenForm(@Valid @ModelAttribute Garden garden, HttpServletRequest request, Errors errors, Model model) {

        String[] bedNames = request.getParameterValues("bedName");

        int[] bedWidthPlots = Arrays.stream(request.getParameterValues("bedWidthPlots"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] bedLengthPlots = Arrays.stream(request.getParameterValues("bedLengthPlots"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 0; i < bedNames.length; i++) {
            int plotTotal = bedWidthPlots[i] * bedLengthPlots[i];

            garden.addBed(bedNames[i], bedWidthPlots[i], bedLengthPlots[i], plotTotal);

        }



        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Garden");
            model.addAttribute(new Garden());
            return "garden/create";
        }

        gardenRepository.save(garden);
        return "redirect:../garden";

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


    @GetMapping("detail")
    public String displayGardenDetails(@RequestParam Integer gardenId, Model model) {

        Optional<Garden> result = gardenRepository.findById(gardenId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + gardenId);
        } else {
            Garden garden = result.get();
            model.addAttribute("title", garden.getName() + " Details");
            model.addAttribute("garden", garden);


        }

        return "garden/detail";
    }


//    @PostMapping("detail")
//    public String processGardenDetails(HttpServletRequest request, Model model){
//
//        String[] plantHereClickedList = request.getParameterValues("plantHereClicked");
//
//        List<Boolean> bedNamesList = new ArrayList<>();
//        for (String plantHereClicked : plantHereClickedList) {
//            bedNamesList.add(Boolean.parseBoolean(plantHereClicked));
//        }
//
//
//
//
//
//        return "redirect:";
//    }


}
