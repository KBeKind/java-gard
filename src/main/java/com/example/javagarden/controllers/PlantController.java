package com.example.javagarden.controllers;


import com.example.javagarden.data.PlantRepository;
import com.example.javagarden.models.Plant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("plants")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @GetMapping
    public String displayAllPlants(Model model) {

        model.addAttribute("title", "All plants");
        model.addAttribute("plants", plantRepository.findAll());

        return "plants/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Plant");
        model.addAttribute(new Plant());
        return "plants/create";
    }

    @PostMapping("create")
    public String processCreatePlantForm(@ModelAttribute @Valid Plant newPlant, Errors errors, Model model){

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        plantRepository.save(newPlant);
        return "redirect:";

    }



}
