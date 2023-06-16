package com.example.javagarden.controllers;

import com.example.javagarden.data.PlantTimeRepository;
//import com.example.javagarden.data.UserRepository;
import com.example.javagarden.data.UserRepository;
import com.example.javagarden.models.PlantTime;


import com.example.javagarden.models.User;
import com.example.javagarden.models.UserGardenData;
import com.example.javagarden.service.DeleteService;
import com.example.javagarden.service.UserGardenDataService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("planttime")
@SessionAttributes("user")
public class PlantTimeController {

    @Autowired
    private PlantTimeRepository plantTimeRepository;

    @Autowired
    private UserGardenDataService userGardenDataService;

    @GetMapping
    public String displayAllPlantTimes(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        model.addAttribute("title", "All Plant Times");
        model.addAttribute("plantTimes",userGardenData.getPlantTimes());
        return "planttime/index";
    }

    @GetMapping("create")
    public String renderCreatePlantTimeForm(Model model) {

        PlantTime plantTime = new PlantTime();

        model.addAttribute("title", "Create Plant Time");
        model.addAttribute("plantTime", plantTime);
        return "planttime/create";
    }


    @PostMapping("create")
    public String processCreatePlantTimeForm(@Valid @ModelAttribute PlantTime plantTime,
                                             Errors errors, Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);


        plantTime.setUserGardenData(userGardenData);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Plant Time");
            return "planttime/create";
        }

        plantTimeRepository.save(plantTime);
        return "redirect:../planttime";
    }


    @GetMapping("delete")
    public String displayDeletePlantTimeForm(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        model.addAttribute("title", "Delete Plant Times");
        model.addAttribute("plantTimes", userGardenData.getPlantTimes());
        return "planttime/delete";
    }

    @PostMapping("delete")
    public String processDeletePlantTimeForm(@RequestParam(required = false) int[] plantTimeIds) {

        DeleteService.deleteData( plantTimeIds, plantTimeRepository);

        return "redirect:../plant";

    }


}
