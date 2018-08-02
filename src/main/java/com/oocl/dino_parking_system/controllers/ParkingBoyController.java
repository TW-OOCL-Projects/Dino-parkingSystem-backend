package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.services.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/parkingBoys")
@RestController
public class ParkingBoyController {

	@Autowired
	ParkingBoyService parkingBoyService;

    @GetMapping(path = "/{id}/parkingLots")
    public ResponseEntity findAllNotFullParkingLots(@PathVariable Long id) {
        List<ParkingLotTinyDTO> parkingLots = parkingBoyService.findAllNotFullParkingLots(id);
        if(parkingLots!=null){
        	return ResponseEntity.ok(parkingLots);
        } else{
        	return ResponseEntity.notFound().build();
        }
    }
}
