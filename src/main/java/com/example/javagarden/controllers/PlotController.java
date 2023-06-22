package com.example.javagarden.controllers;


import com.example.javagarden.data.PlantingRepository;
import com.example.javagarden.data.PlotRepository;
import com.example.javagarden.models.*;
import com.example.javagarden.models.dto.PlotDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.javagarden.service.UserGardenDataService;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("plot")
public class PlotController {


    @Autowired
    private PlantingRepository plantingRepository;

    @Autowired
    private PlotRepository plotRepository;


    @Autowired
    private UserGardenDataService userGardenDataService;


    @GetMapping("detail")
    public String displayPlotDetails(@RequestParam Integer plotId, @RequestParam(required = false) Integer editDateType, Model model, HttpServletRequest request) {


        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        List<Garden> gardens = userGardenData.getGardens();

        //To update days until harvest and days until remove
        for (Garden garden : gardens) {
            for (Bed bed : garden.getBeds()) {
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
        }


        //To find current plot from userGardenData garden
        for (Garden garden : gardens) {
            for (Bed bed : garden.getBeds()) {
                for (Plot plot : bed.getPlots()) {
                    if (plot.getId() == plotId) {

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");

                        if(plot.getPlanting().getHarvestStartDate() != null) {
                            String formattedHarvestDate = formatter.format(plot.getPlanting().getHarvestStartDate());
                            model.addAttribute("formattedHarvestDate", formattedHarvestDate);
                        }

                        if(plot.getPlanting().getRemoveDate() != null) {
                            String formattedRemoveDate = formatter.format(plot.getPlanting().getRemoveDate());
                            model.addAttribute("formattedRemoveDate", formattedRemoveDate);
                        }

                        String formattedPlantingDate = formatter.format(plot.getPlanting().getPlantingDate());
                        model.addAttribute("formattedPlantingDate", formattedPlantingDate);


                        PlotDTO plotDTO = new PlotDTO();
                        Date date = new Date();
                        plotDTO.setEditDate(date);
                        plotDTO.setEditDateType(editDateType);

                        model.addAttribute("title", "Garden: " + garden.getName() + "| Bed:" +bed.getName() + "| Plot Details:");
                        model.addAttribute("plot", plot);
                        model.addAttribute("editDateType", editDateType);
                        model.addAttribute("plotDTO", plotDTO);


                        return "plot/detail";
                    }
                }
            }
        }

        // IF NOT FOUND
        model.addAttribute("title", "Invalid Plot Id: " + plotId);
        model.addAttribute("explanation", "The plot you are looking for does not exist.");
        return "plot/detail";
    }



    @PostMapping("detail")
    public String postPlotDetails( @ModelAttribute PlotDTO plotDTO, @RequestParam Integer plotId, @RequestParam(value="button", required=true) String button,
                                  Model model, HttpServletRequest request){


        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);
        Date date = plotDTO.getEditDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        //To find current plot and planting from userGardenData garden


        for (Garden garden : userGardenData.getGardens()) {
            for (Bed bed : garden.getBeds()) {
                for (Plot plot : bed.getPlots()) {
                    if (plot.getId() == plotId) {

                        Planting planting = plot.getPlanting();


                        if (plotDTO.getEditDateType() == 1) {

                            planting.deleteHarvestStartDate();


                            if(button.equals("Update")){
                                planting.setHarvestStartDate(localDate);
                            }

                            plantingRepository.save(planting);

                            return "redirect:/plot/detail?plotId=" + plotId ;

                        } else if (plotDTO.getEditDateType() == 2) {

                            planting.deleteRemoveDate();

                            if(button.equals("Update")) {
                                planting.setRemoveDate(localDate);
                            }
                            plantingRepository.save(planting);

                            return "redirect:/plot/detail?plotId=" + plotId ;
                        }

                    }
                }
            }
        }


        return "redirect:/plot/detail?plotId=" + plotId ;

    }


}
