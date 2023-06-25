package com.example.javagarden.controllers;

import com.example.javagarden.models.dto.PlantingDTO;
import com.example.javagarden.data.*;

import com.example.javagarden.models.dto.GardenStartDTO;
import com.example.javagarden.models.*;

import com.example.javagarden.service.DeleteService;
import com.example.javagarden.service.UserGardenDataService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    private UserGardenDataService userGardenDataService;

    @GetMapping()
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
        return "redirect:../garden/detail?gardenId=" + garden.getId();

    }

    @GetMapping("delete")
    public String displayDeleteGardenForm(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);
        List<Garden> gardens = userGardenData.getGardens();

        model.addAttribute("title", "Delete Gardens");
        model.addAttribute("gardens", gardens);
        return "garden/delete";
    }

    @PostMapping("delete")
    public String processDeleteGardenForm(@RequestParam(required = false) int[] gardenIds, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);


        for (Garden garden : userGardenData.getGardens()) {

            for (Integer gardenId : gardenIds){

                if (garden.getId() == gardenId){

                    for (Bed bed : garden.getBeds()) {

                        for (Plot plot: bed.getPlots()){

                            if (plot.hasPlanting()){

                                Planting planting = plot.getPlanting();
                                plot.removePlanting(planting);
                                plantingRepository.delete(planting);

                            }

                        }

                    }

                }

            }

        }

        DeleteService.deleteData(gardenIds, gardenRepository);

        return "redirect:../garden";
    }

    @GetMapping("detail")
    public String displayGardenDetails(@RequestParam Integer gardenId, HttpServletRequest request, Model model) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        Optional<Garden> result = gardenRepository.findById(gardenId);




        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + gardenId);
        } else {
            Garden garden = result.get();

            for (Bed bed: garden.getBeds()) {
                for (Plot plot : bed.getPlots()) {
                    if (plot.hasPlanting()) {
                        if (plot.getPlanting().getHarvestStartDate() != null) {
                            plot.getPlanting().updateDaysUntilHarvestStartDate();
                        }
                        if (plot.getPlanting().getRemoveDate() != null) {
                            plot.getPlanting().updateDaysUntilRemoveStartDate();
                        }
                    }

                }
            }
            model.addAttribute("title", garden.getName() + " Details");
            model.addAttribute("garden", garden);
            model.addAttribute("plants", userGardenData.getPlants());


            PlantingDTO plantingDTO = new PlantingDTO();
            plantingDTO.setGardenId(gardenId);
            model.addAttribute("plantingDTO", plantingDTO);

//            Integer plantId=0;
//            model.addAttribute("plantId", plantId);

        }
        return "garden/detail";
    }


    @PostMapping("detail")
    public String processGardenDetails(@Valid @ModelAttribute PlantingDTO plantingDTO, @RequestParam(required = false) Integer plantId,
                                       @RequestParam(required = false) Integer plotId, Errors errors, Model model) {


        if (errors.hasErrors()) {
            return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId();
        }

        if(plantId == null){

            return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId();

        }


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
                return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId() + "#" + plot.getId();

            } else {


                Optional<Plant> plantResult = plantRepository.findById(plantId);
                if (plantResult.isEmpty()) {
                    model.addAttribute("title", "Invalid Category ID: ");
//                  ADD ERROR HANDLING IF NEEDED
                    return "redirect:../";

                } else {

                    // DATE STUFF FOR CALCULATION OF TIME LEFT
                    LocalDateTime localDateTime = LocalDateTime.now();
                    LocalDate localDate = localDateTime.toLocalDate();

                    Plant plant = plantResult.get();

                    Planting planting = new Planting(plot, plant, localDate);
                    LocalDate harvestStartDate = localDate.plusDays(plant.getPlantTime().getDaysUntilHarvestTotal());
                    LocalDate removeStartDate = localDate.plusDays(plant.getPlantTime().getDaysUntilPlantRemoveTotal());

                    if (harvestStartDate != localDate){
                        planting.setHarvestStartDate(harvestStartDate);

                        Integer setDaysUntilHarvestStartDate = ((int) ChronoUnit.DAYS.between(planting.getPlantingDate(), planting.getHarvestStartDate()));

                        if (setDaysUntilHarvestStartDate == 0) {
                            planting.setDaysUntilHarvestStartDate(setDaysUntilHarvestStartDate);

                        }

                    }
                    if (removeStartDate != localDate){
                        planting.setRemoveDate(removeStartDate);

                        Integer setDaysUntilRemoveStartDate = ((int) ChronoUnit.DAYS.between(planting.getPlantingDate(), planting.getRemoveDate()));

                        if (setDaysUntilRemoveStartDate != 0) {
                            planting.setDaysUntilRemoveStartDate(setDaysUntilRemoveStartDate);

                        }

                    }

                    plot.setPlanting(planting);
                    plantingRepository.save(planting);

                    return "redirect:/garden/detail?gardenId=" + plantingDTO.getGardenId() + "#" + plot.getId();


                }

            }


        }
    }
}