package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.Order;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order generateOrder(String plateNumber, String receiptId) {
        Order order = new Order("park", plateNumber, "nohandle", receiptId);
        return orderRepository.save(order);
    }
}
