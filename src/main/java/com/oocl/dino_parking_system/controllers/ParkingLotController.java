package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.ParkingLotDashBoardDTO;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.services.ParkingLotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/parkingLots")
public class ParkingLotController {
	private final ParkingLotsService parkingLotsService;

	@Autowired
	public ParkingLotController(ParkingLotsService parkingLotsService) {
		this.parkingLotsService = parkingLotsService;
	}

	@Transactional
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createParkingLots(@RequestBody ParkingLot parkingLot) {
		if (parkingLotsService.createParkingLots(parkingLot)) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Transactional
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllParkingLots(@RequestParam(value = "id", required = false) Long id,
	                                        @RequestParam(value = "name", required = false) String name,
	                                        @RequestParam(value = "eq", required = false) Integer eq,
	                                        @RequestParam(value = "gt", required = false) Integer gt,
	                                        @RequestParam(value = "lt", required = false) Integer lt) {
		if (id != null) {
			// 根据ID查找
			return new ResponseEntity<>(parkingLotsService.findParkingLotById(id), HttpStatus.OK);
		}
		if (name != null && eq != null) {
			// TODO:组合查询
		} else if (name != null) {
			// TODO:根据名字模糊查询
//			return new ResponseEntity<>(parkingLotsService.findAllParkingLotsByName(name), HttpStatus.OK);
		} else if (gt != null && lt != null) {
			// 根据大小范围查找
			return new ResponseEntity<>(parkingLotsService.findAllParkingLotsBySizeBetween(lt, gt), HttpStatus.OK);
		} else if (gt != null) {
			// 大于等于
			return new ResponseEntity<>(parkingLotsService.findAllParkingLotsBySizeGreaterThanEqual(gt), HttpStatus.OK);
		} else if (lt != null) {
			// TODO: 小于等于
//			return new ResponseEntity<>(parkingLotsService.findAllParkingLotsBySizeLessThanEqual(lt), HttpStatus.OK);
		}
		return new ResponseEntity<>(parkingLotsService.getAllParkingLots(), HttpStatus.OK);

	}

	@Transactional
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateParkingLots(@PathVariable long id, @RequestBody ParkingLot parkingLot) {
		if (parkingLotsService.updateParkingLots(id, parkingLot)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Transactional
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity changeParkingLotStatus(@PathVariable long id) {
		if (parkingLotsService.changeParkingLotStatus(id)) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Transactional
	@GetMapping(path = "/dashboard")
	public List<ParkingLotDashBoardDTO> findAllParkingLotDashBoard() {
		return parkingLotsService.findAllParkingLotDashBoard();
	}

	@Transactional
	@GetMapping(path = "/dashboard/page/{page}/pageSize/{size}")
	public List<ParkingLotDashBoardDTO> findAllParkingLotDashBoard(@PathVariable int page,
	                                                               @PathVariable int size) {
		return parkingLotsService.findAllParkingLotDashBoardByPaging(new PageRequest(page, size));
	}

}
