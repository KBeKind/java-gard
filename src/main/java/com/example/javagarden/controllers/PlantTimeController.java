package com.example.javagarden.controllers;

import com.example.javagarden.data.PlantTimeRepository;
import com.example.javagarden.models.PlantTime;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("planttime")
public class PlantTimeController {

    @Autowired
    private PlantTimeRepository plantTimeRepository;

    @GetMapping
    public String displayAllPlantTimes(Model model) {
        model.addAttribute("title", "All Plant Times");
        model.addAttribute("plantTimes", plantTimeRepository.findAll());
        return "planttime/index";
    }

    @GetMapping("create")
    public String renderCreatePlantTimeForm(Model model) {
        model.addAttribute("title", "Create Plant Time");
        model.addAttribute(new PlantTime());
        return "planttime/create";
    }



    @PostMapping("create")
    public String processCreateEventCategoryForm(@Valid @ModelAttribute PlantTime plantTime,
                                                 Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Plant Time");
            model.addAttribute(new PlantTime());
            return "planttime/create";
        }

        plantTimeRepository.save(plantTime);
        return "redirect:../planttime";
    }

}
