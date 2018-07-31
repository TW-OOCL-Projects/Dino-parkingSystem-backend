package com.oocl.dino_parking_system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/parkingBoy")
@RestController
public class ParkingBoyController {

    @GetMapping(path = "")
    public String index(){
        return "parkingBoy";
    }
}
