package com.example.javagarden.controllers;

import com.example.javagarden.data.*;

import com.example.javagarden.models.Plant;

import com.example.javagarden.models.UserGardenData;
import com.example.javagarden.service.DeleteService;
import com.example.javagarden.service.UserGardenDataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private UserGardenDataService userGardenDataService;

    @GetMapping
    public String displayPlants(@RequestParam(required = false) Integer plantId, Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        if (plantId == null) {
            model.addAttribute("title", "All Plants");
            model.addAttribute("plants", userGardenData.getPlants());

        } else {
            Optional<Plant> result = plantRepository.findById(plantId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Plant ID: " + plantId);
            } else {
                Plant plant = result.get();
                model.addAttribute("title", "Plant: " + plant.getName());
                model.addAttribute("plant", plant);
            }
        }

        return "plant/index";
    }


    @GetMapping("create")
    public String displayCreatePlantForm(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        model.addAttribute("title", "Create Plant");
        model.addAttribute(new Plant());
        model.addAttribute("plantTimes", userGardenData.getPlantTimes());
        model.addAttribute("plantIcons", plantIconRepository.findAll());

        return "plant/create";
    }


    @PostMapping("create")
    public String processCreatePlantForm(@Valid @ModelAttribute Plant plant,
                                                 Errors errors, Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Plant");
            model.addAttribute("plantTimes", userGardenData.getPlantTimes());
            model.addAttribute("plantIcons", plantIconRepository.findAll());
            return "plant/create";
        }

        plant.setUserGardenData(userGardenData);
        plantRepository.save(plant);
        return "redirect:../plant";
    }


    @GetMapping("delete")
    public String displayDeletePlantForm(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        model.addAttribute("title", "Delete Plants");
        model.addAttribute("plants", userGardenData.getPlants());
        return "plant/delete";
    }

    @PostMapping("delete")
    public String processDeletePlantForm(@RequestParam(required = false) int[] plantIds) {
        DeleteService.deleteData( plantIds, plantRepository);
        return "redirect:../plant";
    }

    @GetMapping("detail")
    public String displayPlantDetails(@RequestParam Integer plantId, Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        List<Plant> plants = userGardenData.getPlants();

        for (Plant plant : plants) {

            if(plant.getId() == plantId) {
                model.addAttribute("title", plant.getName() + " Details");
                model.addAttribute("plant", plant);
                return "plant/detail";
            }

        }

        // IF NOT FOUND
        model.addAttribute("title", "Invalid Plant Id: " + plantId);
        model.addAttribute("explanation", "The plant you are looking for does not exist.");
        return "plant/detail";
    }

}
