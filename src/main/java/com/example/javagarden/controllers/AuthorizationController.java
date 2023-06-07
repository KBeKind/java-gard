package com.example.javagarden.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthorizationController {

    @GetMapping("/oops")
    public String displayOops() {
        return "oops";

    }

    @GetMapping("admin/index")
    public String displayAdmin() {
        return "admin/index";

    }

}

