package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.dto.ParkingBoyDTO;
import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.UserService;
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
    OrderService service;

    @Autowired
    UserService parkingBoyService;

    @Transactional
    @PostMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generateOrder(@RequestBody LotOrder lotOrder){
        boolean key = service.generateOrder(lotOrder);
        if(key){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> getAllOrders(){
        List<LotOrder> lotOrders = service.getAllOrders();
        List<OrderDTO> orderDTOS = lotOrders.stream().map(OrderDTO::new).collect(Collectors.toList());
        return orderDTOS;
    }

    //员工抢单页面
    @Transactional
    @GetMapping(path = "/{status}")
    public List<LotOrder> getOrdersByStatus(@PathVariable String status){
        List<LotOrder> lotOrders = service.getOrdersByStatus(status);
        return lotOrders;
    }

    @Transactional
    @PutMapping(path = "/{id}/parkingBoys")
    public ResponseEntity changeStatus(@PathVariable("id") Long id, @RequestBody ParkingBoyDTO parkingBoyDTO){

        User parkingBoy = parkingBoyService.getUserById(parkingBoyDTO.getId());
        Boolean key = service.changeStatus(id,parkingBoy);
        if(key)
            return ResponseEntity.status(HttpStatus.OK).build();
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
