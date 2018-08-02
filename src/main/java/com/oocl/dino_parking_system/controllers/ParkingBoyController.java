package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.services.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/parkingBoys")
@RestController
public class ParkingBoyController {

	@Autowired
	ParkingBoyService parkingBoyService;

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
}
