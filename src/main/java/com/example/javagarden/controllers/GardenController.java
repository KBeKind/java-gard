package com.example.javagarden.controllers;

import com.example.javagarden.models.dto.PlantingDTO;
import com.example.javagarden.data.*;

import com.example.javagarden.models.dto.GardenStartDTO;
import com.example.javagarden.models.*;

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

import java.util.Arrays;
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

    @Autowired
    private UserGardenDataService userGardenDataService;

    @GetMapping
    public String displayGarden(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        model.addAttribute("title", "Gardens");
        model.addAttribute("gardens", userGardenData.getGardens());
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

        String[] bedNames = request.getParameterValues("bedName");

        int[] bedWidthPlots = Arrays.stream(request.getParameterValues("bedWidthPlots"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] bedLengthPlots = Arrays.stream(request.getParameterValues("bedLengthPlots"))
                .mapToInt(Integer::parseInt)
                .toArray();


        for (int i = 0; i < bedNames.length; i++) {
            int plotTotal = bedWidthPlots[i] * bedLengthPlots[i];
            garden.addBed(bedNames[i], bedWidthPlots[i], bedLengthPlots[i], plotTotal);

        }

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Garden");
            model.addAttribute(new Garden());
            return "garden/create";
        }

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        garden.setUserGardenData(userGardenData);

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


            PlantingDTO plantingDTO = new PlantingDTO();
            plantingDTO.setGardenId(gardenId);
            model.addAttribute("plantingDTO", plantingDTO);

            Integer plantId=0;
            model.addAttribute("plantId", plantId);

        }
        return "garden/detail";
    }


    @PostMapping("detail")
    public String processGardenDetails(@Valid @ModelAttribute PlantingDTO plantingDTO, @RequestParam(required = false) Integer plantId, @RequestParam(required = false) Integer plotId, Model model) {

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

                Optional<Plant> plantResult = plantRepository.findById(plantId);
                if (plantResult.isEmpty()) {
//                    model.addAttribute("title", "Invalid Category ID: ");
//                  ADD ERROR HANDLING IF NEEDED
                    return "redirect:../";

                } else {

                    Plant plant = plantResult.get();

                    Planting planting = new Planting(plot, plant);
                    plot.setPlanting(planting);
                    plantingRepository.save(planting);
                    return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId();

                }

            }


        }
    }
}