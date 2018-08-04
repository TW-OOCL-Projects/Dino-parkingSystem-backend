package com.oocl.dino_parking_system.controllers;


import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.dto.ParkingBoyInfoDTO;
import com.oocl.dino_parking_system.dto.ParkingBoyTinyDTO;
import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_WAITPARK;
import static com.oocl.dino_parking_system.constants.Constants.STATUS_WAITUNPARK;

@RequestMapping("/parkingBoys")
@RestController
public class ParkingBoyController {


    @Autowired
    ParkingBoyService parkingBoyService;

    @Autowired
    OrderService orderService;

    //获取所有的小弟
    @GetMapping(path = "")
    public List<ParkingBoyTinyDTO> findAllNormalParkingBoy(){
    	return parkingBoyService.findAllParkingBoys().stream()
			    .map(ParkingBoyTinyDTO::new)
			    .collect(Collectors.toList());
    }

    //获取小弟手下管理的所有停车场
	@GetMapping(path = "/{id}/parkingLots")
	public List<ParkingLotTinyDTO> findAllManagedParkingLots(@PathVariable Long id){
    	return parkingBoyService.findAllManagedParkingLots(id).stream()
			    .map(ParkingLotTinyDTO::new)
			    .collect(Collectors.toList());
	}

    // 获取停车小弟管理的停车场中未满的停车场
    @GetMapping(path = "/{id}/noFullParkingLots")
    public ResponseEntity findAllNotFullParkingLots(@PathVariable Long id) {
        List<ParkingLotTinyDTO> parkingLots = parkingBoyService.findAllNotFullParkingLots(id);
        if (parkingLots != null) {
            return ResponseEntity.ok(parkingLots);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	// 返回所有（待处理）订单
	@Transactional
	@GetMapping(path = "/{parkingBoyId}/noHandleOrders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getAllOrders(@PathVariable Long parkingBoyId) {
			List<OrderDTO> orders = orderService.findOrderByParkingBoyId(parkingBoyId).stream()
					.filter(orderDTO -> orderDTO.getStatus().equals(STATUS_WAITPARK) || orderDTO.getStatus().equals(STATUS_WAITUNPARK))
					.collect(Collectors.toList());
			return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	// 返回所有小弟完成的停、取车订单
    @Transactional
    @GetMapping(path = "/{parkingBoyId}/historyOrders")
    public List<OrderDTO> findAllFinishOrderByParkingBoyId(@PathVariable Long parkingBoyId){
    	return parkingBoyService.findAllFinishOrderByParkingBoyId(parkingBoyId);
    }

    //小弟停车到指定停车场
	@PutMapping(path = "/{parkingBoyId}/parkingLots/{parkingLotId}")
	public ResponseEntity parkCar(@PathVariable Long parkingBoyId,
	                              @PathVariable Long parkingLotId,
	                              @RequestBody JSONObject request) {
		Long orderId = Long.valueOf(request.get("orderId").toString());
		if (parkingBoyService.parCar(parkingBoyId, parkingLotId, orderId)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}
