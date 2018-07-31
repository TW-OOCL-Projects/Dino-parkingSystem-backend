package com.oocl.dino_parking_system.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping(path = "/")
    public String index(){
        return "Hello,Dino!";
    }
}

