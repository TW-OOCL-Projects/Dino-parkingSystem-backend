package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.Order;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.oocl.dino_parking_system.constants.Constants.INUSE;
import static com.oocl.dino_parking_system.constants.Constants.PARKCAR;

@Component
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean generateOrder(Order order) {
        order.setStatus(INUSE);
        order.setType(PARKCAR);
        orderRepository.save(order);
        return true;
    }
}
