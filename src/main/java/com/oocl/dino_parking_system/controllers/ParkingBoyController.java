package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.ParkingBoyInfoDTO;
import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/parkingBoys")
@RestController
public class ParkingBoyController {

    @Autowired
    ParkingBoyService parkingBoyService;

    @Autowired
    OrderService orderService;

    // 获取停车小弟管理的停车场中未满的停车场
    @GetMapping(path = "/{id}/parkingLots")
    public ResponseEntity findAllNotFullParkingLots(@PathVariable Long id) {
        List<ParkingLotTinyDTO> parkingLots = parkingBoyService.findAllNotFullParkingLots(id);
        if (parkingLots != null) {
            return ResponseEntity.ok(parkingLots);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{parkingBoyId}/parkingLots/{parkingLotId}")
    public ResponseEntity parkCar(@PathVariable Long parkingBoyId,
                                  @PathVariable Long parkingLotId) {
        if (parkingBoyService.parCar(parkingBoyId, parkingLotId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(path = "")
    public ResponseEntity<List<ParkingBoyInfoDTO>> findAllParkingBoys() {
        List<User> parkingBoys = parkingBoyService.findAllParkingBoys();
        List<ParkingBoyInfoDTO> parkingBoyList = new ArrayList<>();
        try {
            parkingBoyList = parkingBoys.stream().map(ParkingBoyInfoDTO::new).collect(Collectors.toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(parkingBoyList);
    }
}
