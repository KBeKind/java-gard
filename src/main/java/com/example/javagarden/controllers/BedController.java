package com.example.javagarden.controllers;

import com.example.javagarden.data.BedRepository;
import com.example.javagarden.data.GardenRepository;
import com.example.javagarden.models.Bed;
import com.example.javagarden.models.Garden;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("bed")
public class BedController {


    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private GardenRepository gardenRepository;

//    @GetMapping
//    public String displayGarden( Model model) {
//
//        model.addAttribute("title", "Gardens");
//        model.addAttribute("gardens", gardenRepository.findAll());
//
//        return "garden/index";
//    }

@GetMapping("create")
public String displayCreateBedForm(@RequestParam int gardenId, Model model) {

    Optional<Garden> result = gardenRepository.findById(gardenId);

    if (result.isEmpty()) {
        model.addAttribute("title", "Invalid Garden ID: " + gardenId);
    } else {

    }
        Garden garden = result.get();

    int[] numbers = {1, 2, 3, 4, 5};

        model.addAttribute("title", "Create Beds");

//        model.addAttribute("beds", garden.getBeds());

        model.addAttribute("numbers", numbers);

    return "bed/create";
}



//
//    @PostMapping("create")
//    public String processCreateGardenForm(@Valid @ModelAttribute Bed bed,
//                                          Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Create Bed");
//            model.addAttribute(new Bed());
//            return "bed/create";
//        }
//
//
//
//
//        bedRepository.save(bed);
//
//        return "redirect:../bed/create";
//    }
//
}




//    @GetMapping("create")
//    public String displayCreateGardenForm(Model model) {
//        model.addAttribute("title", "Create Garden");
//        model.addAttribute(new Garden());
////        model.addAttribute("plantTimes", plantTimeRepository.findAll());
//        return "garden/create";
//    }
//
//
//    @PostMapping("create")
//    public String processCreateGardenForm(@Valid @ModelAttribute Garden garden,
//                                          Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Create Garden");
//            model.addAttribute(new Garden());
//            return "garden/create";
//        }
//
//        gardenRepository.save(garden);
//
//        return "redirect:../bed/create";
//    }
//
//
//    @GetMapping("delete")
//    public String displayDeleteGardenForm(Model model) {
//        model.addAttribute("title", "Delete Gardens");
//        model.addAttribute("gardens", gardenRepository.findAll());
//        return "garden/delete";
//    }
//
//    @PostMapping("delete")
//    public String processDeleteGardenForm(@RequestParam(required = false) int[] gardenIds) {
//
//        DeleteService.deleteData( gardenIds, gardenRepository);
//
//        return "redirect:../garden";
//
//    }