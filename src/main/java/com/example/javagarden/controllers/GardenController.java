package com.example.javagarden.controllers;

import com.example.javagarden.controllers.dto.PlantingDTO;
import com.example.javagarden.data.*;

import com.example.javagarden.controllers.dto.GardenStartDTO;
import com.example.javagarden.models.*;

import com.example.javagarden.service.DeleteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("garden")
public class GardenController {


    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlotRepository plotRepository;

    @Autowired
    private PlantingRepository plantingRepository;

    @GetMapping
    public String displayGarden(Model model) {
        model.addAttribute("title", "Gardens");
        model.addAttribute("gardens", gardenRepository.findAll());
        return "garden/index";
    }


    @GetMapping("start")
    public String displayStartGardenForm(Model model) {

        GardenStartDTO gardenStartDTO = new GardenStartDTO();
        gardenStartDTO.setBedNum(1);

        model.addAttribute("title", "Start Garden");
        model.addAttribute("gardenStartDTO", gardenStartDTO);

        return "garden/start";
    }

    @PostMapping("start")
    public String processStartGardenForm(@ModelAttribute @Valid GardenStartDTO gardenStartDTO, Errors errors, Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Start Garden");
            return "garden/start";
        }

        int bedNum = gardenStartDTO.getBedNum();
        String name = gardenStartDTO.getName();

        return "redirect:../garden/create?bedNum=" + bedNum + "&name=" + name;
    }

    @GetMapping("create")
    public String displayCreateGardenForm(@RequestParam(value = "bedNum") int bedNum, @RequestParam("name") String name, Model model) {


        Garden garden = new Garden(name);


        model.addAttribute("title", "Create Garden");
        model.addAttribute("garden", garden);
        model.addAttribute("bedNum", bedNum);


        return "garden/create";
    }

    @PostMapping("create")
    public String processCreateGardenForm(@Valid @ModelAttribute Garden garden, HttpServletRequest request, Errors errors, Model model) {


        int[] selectedIconList = Arrays.stream(request.getParameterValues("selectedIcon"))
                .mapToInt(Integer::parseInt)
                .toArray();




        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Garden");
            model.addAttribute(new Garden());
            return "garden/create";
        }

        gardenRepository.save(garden);
        return "redirect:../garden";

    }


    @GetMapping("delete")
    public String displayDeleteGardenForm(Model model) {
        model.addAttribute("title", "Delete Gardens");
        model.addAttribute("gardens", gardenRepository.findAll());
        return "garden/delete";
    }

    @PostMapping("delete")
    public String processDeleteGardenForm(@RequestParam(required = false) int[] gardenIds) {
        DeleteService.deleteData(gardenIds, gardenRepository);
        return "redirect:../garden";

    }


    @GetMapping("detail")
    public String displayGardenDetails(@RequestParam Integer gardenId, Model model) {
        Optional<Garden> result = gardenRepository.findById(gardenId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + gardenId);
        } else {
            Garden garden = result.get();
            model.addAttribute("title", garden.getName() + " Details");
            model.addAttribute("garden", garden);
            model.addAttribute("plants", plantRepository.findAll());

            int columns = 4;
            model.addAttribute("columns", columns);
            PlantingDTO plantingDTO = new PlantingDTO();
            plantingDTO.setGardenId(gardenId);
            model.addAttribute("plantingDTO", plantingDTO);

            String plantName = "";
            model.addAttribute("plantName", plantName);

        }
        return "garden/detail";
    }


    @PostMapping("detail")
    public String processGardenDetails(@Valid @ModelAttribute PlantingDTO plantingDTO, @RequestParam(required = false) String plantName, @RequestParam(required = false) Integer plotId, Model model) {


        Optional<Plot> result = plotRepository.findById(plotId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Category ID: ");

            return "redirect:../";

        } else {

                            Plot plot = result.get();

            if (plot.hasPlanting()) {

                Planting planting = plot.getPlanting();
                plot.removePlanting(planting);
                plantingRepository.delete(planting);
                return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId();

            } else {

                Planting planting = new Planting(plot, plantName);
                plot.setPlanting(planting);
                plantingRepository.save(planting);
                return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId();

            }

        }


    }
}