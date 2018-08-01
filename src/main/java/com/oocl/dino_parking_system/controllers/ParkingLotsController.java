package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ParkingLotsService;
import com.oocl.dino_parking_system.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotsController {
//    @Autowired
   private ParkingLotsService parkingLotsService;


    @Autowired
    public ParkingLotsController(ParkingLotsService parkingLotsService) {
        this.parkingLotsService= parkingLotsService;
    }
    @Transactional
    @PostMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createParkingLots(@RequestBody ParkingLot parkingLot){
        if (parkingLotsService.createParkingLots(parkingLot)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Transactional
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllParkingLots(){
        if (parkingLotsService.getAllParkingLots()!=null){
            return new ResponseEntity<List<ParkingLotTinyDTO>>(parkingLotsService.getAllParkingLots(),HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateParkingLots(@PathVariable long id,@RequestBody ParkingLot parkingLot){
        if (parkingLotsService.updateParkingLots(id,parkingLot)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Transactional
    @PatchMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity freezeParkingLots(@PathVariable long id) {
	    if (parkingLotsService.changeParkingLotStatus(id)) {
		    return ResponseEntity.status(HttpStatus.OK).build();
	    } else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
    }

}
