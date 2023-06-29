package com.example.javagarden.controllers;


import com.example.javagarden.models.UserGardenData;
import com.example.javagarden.service.UserGardenDataService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping()
public class HomeController {

    @Autowired
    private UserGardenDataService userGardenDataService;


    @GetMapping("/")
    public String transferToHomeFromIndex(Model model) {
        return "home";
    }


    @GetMapping("/home")
    public String displayHome(Model model) {
        return "home";
    }


    @GetMapping("/userhome")
    public String displayUserHome(Model model, HttpServletRequest request) {

        UserGardenData userGardenData = userGardenDataService.getUserGardenData(request);

        String username = userGardenData.getUser().getUsername();

        model.addAttribute("username", username);

        return "userhome";
    }




}
