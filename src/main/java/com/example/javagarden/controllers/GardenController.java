package com.example.javagarden.controllers;

import com.example.javagarden.data.GardenRepository;
import com.example.javagarden.data.PlantRepository;
import com.example.javagarden.data.PlantTimeRepository;
import com.example.javagarden.models.Garden;
import com.example.javagarden.models.Plant;
import com.example.javagarden.models.PlantTime;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        return "garden/create";
    }


    @PostMapping("create")
    public String processCreatePlantForm(@Valid @ModelAttribute Garden garden,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Garden");
            model.addAttribute(new Garden());
            return "garden/create";
        }

        gardenRepository.save(garden);

        return "redirect:../garden";
    }


//    @GetMapping("delete")
//    public String displayDeletePlantForm(Model model) {
//        model.addAttribute("title", "Delete Plants");
//        model.addAttribute("plants", plantRepository.findAll());
//        return "plant/delete";
//    }
//
//    @PostMapping("delete")
//    public String processDeletePlantForm(@RequestParam(required = false) int[] plantIds) {
//
//        if (plantIds != null) {
//            for (int id : plantIds) {
//                plantRepository.deleteById(id);
//            }
//        }
//
//        return "redirect:../plant";
//
//    }
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
