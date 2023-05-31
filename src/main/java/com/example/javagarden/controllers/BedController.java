package com.example.javagarden.controllers;

import com.example.javagarden.data.BedRepository;
import com.example.javagarden.data.GardenRepository;

import com.example.javagarden.models.Garden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("bed")
public class BedController {


    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private GardenRepository gardenRepository;


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


}


