package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.entities.Order;
import com.oocl.dino_parking_system.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService service;

    @Transactional
    @PostMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> generateOrder(@RequestBody Order order){
        boolean key = service.generateOrder(order);
        if(key){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Transactional
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders(){
        List<Order> orders = service.getAllOrders();
        return orders;
    }

}
