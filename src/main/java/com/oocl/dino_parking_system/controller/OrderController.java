package com.oocl.dino_parking_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.service.OrderService;
import com.oocl.dino_parking_system.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	ParkingBoyService parkingBoyService;

	@Transactional
	@GetMapping(path = "")
	public ResponseEntity getAllOrders(){
		return new ResponseEntity<>(orderService.getAllOrders().stream()
				.map(OrderDTO::new)
				.collect(Collectors.toList()), HttpStatus.OK);
	}

	//根据status搜索所有订单
	@Transactional
	@GetMapping(path = "/{status}")
	public List<OrderDTO> getOrdersByStatus(@PathVariable String status) {
		return orderService.getOrdersByStatus(status).stream()
				.map(OrderDTO::new)
				.collect(Collectors.toList());
	}

	// 修改订单状态
	@Transactional
	@PutMapping(path = "/{orderId}")
	public ResponseEntity changeStatus(@PathVariable("orderId") Long orderId, @RequestBody JSONObject request) {
		Long parkingBoyId = Long.valueOf(request.get("parkingBoyId").toString());
		String status = request.get("status").toString();
		User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
		if (parkingBoy != null
				&& parkingBoyService.findAllNotFullParkingLots(parkingBoyId).size() > 0) {
			if (orderService.changeOrderStatus(orderId, parkingBoy, status,null))
				return ResponseEntity.status(HttpStatus.OK).build();
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
