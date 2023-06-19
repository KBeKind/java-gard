package com.example.javagarden.controllers;


import com.example.javagarden.data.PlotRepository;
import com.example.javagarden.models.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.javagarden.service.UserGardenDataService;



import java.util.List;

@Controller
@RequestMapping("plot")
public class PlotController {

    @Autowired
    private UserGardenDataService userGardenDataService;



    @GetMapping("detail")
    public String displayPlotDetails(@RequestParam Integer plotId, Model model, HttpServletRequest request) {

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
                        model.addAttribute("title", "Garden: " + garden.getName() + "| Bed:" +bed.getName() + "| Plot Details:");
                        model.addAttribute("plot", plot);
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





}
