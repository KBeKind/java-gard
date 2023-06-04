package com.example.javagarden.controllers;

import com.example.javagarden.data.PlantIconRepository;

import com.example.javagarden.models.PlantIcon;

import com.example.javagarden.service.DeleteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("planticon")
public class PlantIconController {

    @Autowired
    private PlantIconRepository plantIconRepository;

    @GetMapping
    public String displayAllPlantIcons(Model model) {
        model.addAttribute("title", "All Plant Icons");
        model.addAttribute("plantTimes", plantIconRepository.findAll());
        return "planticon/index";
    }

    @GetMapping("create")
    public String renderCreatePlantIconForm(Model model) {
        model.addAttribute("title", "Create Plant Icon");
        model.addAttribute(new PlantIcon());
        return "plantIcon/create";
    }



    @PostMapping("create")
    public String processCreatePlantIconForm(@Valid @ModelAttribute PlantIcon plantIcon,
                                                 Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Plant Icon");
            model.addAttribute(new PlantIcon());
            return "planticon/create";
        }

        plantIconRepository.save(plantIcon);
        return "redirect:../planticon";
    }


    @GetMapping("delete")
    public String displayDeletePlantIconForm(Model model) {
        model.addAttribute("title", "Delete Plant Icons");
        model.addAttribute("plantIcons", plantIconRepository.findAll());
        return "planticon/delete";
    }

    @PostMapping("delete")
    public String processDeletePlantIconForm(@RequestParam(required = false) int[] plantIconIds) {

         DeleteService.deleteData( plantIconIds, plantIconRepository);

        return "redirect:../plant";

    }


}
