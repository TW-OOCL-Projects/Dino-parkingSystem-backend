package com.oocl.dino_parking_system.controllers;

import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders(){
	    return orderService.getAllOrders().stream()
			    .map(OrderDTO::new)
			    .collect(Collectors.toList());
    }

    @Transactional
    @GetMapping(path = "/{status}")
    public List<OrderDTO> getOrdersByStatus(@PathVariable String status){
        return orderService.getOrdersByStatus(status).stream()
		        .map(OrderDTO::new)
		        .collect(Collectors.toList());
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity changeStatus(@PathVariable("id") Long id, @RequestBody JSONObject request){
		Long parkingBoyId = Long.valueOf(request.get("parkingBoyId").toString());
        User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
	    System.out.println(parkingBoyService.findAllNotFullParkingLots(parkingBoyId));
        if(parkingBoy!=null
		        && parkingBoyService.findAllNotFullParkingLots(parkingBoyId).size()>0){
		    if (orderService.changeStatus(id, parkingBoy))
			    return ResponseEntity.status(HttpStatus.OK).build();
		    else
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }else{
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
    }

    @Transactional
    @GetMapping(path = "/{type}/parkingBoys/{parkingBoyId}")
    public ResponseEntity getOrdersByStatusAndParkingBoyId(@PathVariable String type, @PathVariable Long parkingBoyId){
        List<OrderDTO> orders = orderService.findOrderByParkingBoyId(type,parkingBoyId);
        if(orders.size() > 0){
            return new ResponseEntity(orders,HttpStatus.OK);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
