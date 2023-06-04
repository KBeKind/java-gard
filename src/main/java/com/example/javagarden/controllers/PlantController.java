package com.example.javagarden.controllers;

import com.example.javagarden.data.PlantIconRepository;
import com.example.javagarden.data.PlantRepository;

import com.example.javagarden.data.PlantTimeRepository;
import com.example.javagarden.models.Plant;

import com.example.javagarden.models.PlantTime;
import com.example.javagarden.service.DeleteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("plant")
public class PlantController {

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantTimeRepository plantTimeRepository;

    @Autowired
    private PlantIconRepository plantIconRepository;


    @GetMapping
    public String displayPlants(@RequestParam(required = false) Integer plantTimeId, Model model) {

        if (plantTimeId == null) {
            model.addAttribute("title", "All Plants");
            model.addAttribute("plants", plantRepository.findAll());
        } else {
            Optional<PlantTime> result = plantTimeRepository.findById(plantTimeId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Plant Time ID: " + plantTimeId);
            } else {
                PlantTime plantTime = result.get();
                model.addAttribute("title", "Plant Time: " + plantTime.getName());
                model.addAttribute("plants", plantTime.getPlants());
            }
        }

        return "plant/index";
    }




    @GetMapping("create")
    public String displayCreatePlantForm(Model model) {
        model.addAttribute("title", "Create Plant");
        model.addAttribute(new Plant());
        model.addAttribute("plantTimes", plantTimeRepository.findAll());
        model.addAttribute("plantIcons", plantIconRepository.findAll());




        return "plant/create";
    }


    @PostMapping("create")
    public String processCreatePlantForm(@Valid @ModelAttribute Plant plant,
                                                 Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Plant");
            model.addAttribute(new Plant());
            return "plant/create";
        }

        plantRepository.save(plant);

        return "redirect:../plant";
    }




    @GetMapping("delete")
    public String displayDeletePlantForm(Model model) {
        model.addAttribute("title", "Delete Plants");
        model.addAttribute("plants", plantRepository.findAll());
        return "plant/delete";
    }

    @PostMapping("delete")
    public String processDeletePlantForm(@RequestParam(required = false) int[] plantIds) {
        DeleteService.deleteData( plantIds, plantRepository);
        return "redirect:../plant";
    }

    @GetMapping("detail")
    public String displayPlantDetails(@RequestParam Integer plantId, Model model) {

        Optional<Plant> result = plantRepository.findById(plantId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + plantId);
        } else {
            Plant plant = result.get();
            model.addAttribute("title", plant.getName() + " Details");
            model.addAttribute("plant", plant);
        }

        return "plant/detail";
    }

}
