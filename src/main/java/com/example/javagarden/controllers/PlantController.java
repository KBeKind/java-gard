package com.example.javagarden.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("plants")
public class PlantController {





    @GetMapping
    public String displayAllPlants(Model model) {

        model.addAttribute("title", "All plants");

        return "plants/index";
    }




}
