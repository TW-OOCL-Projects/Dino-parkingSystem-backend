package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_INUSE;
import static com.oocl.dino_parking_system.constants.Constants.STATUS_NOHANDLE;
import static com.oocl.dino_parking_system.constants.Constants.TYPE_PARKCAR;

@Component
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public LotOrder generateOrder(String plateNumber, String receiptId) {
        LotOrder lotOrder = new LotOrder(TYPE_PARKCAR, plateNumber, STATUS_NOHANDLE, receiptId);
        return orderRepository.save(lotOrder);
    }

    public boolean generateOrder(LotOrder lotOrder) {
        lotOrder.setStatus(STATUS_INUSE);
        lotOrder.setType(TYPE_PARKCAR);
        orderRepository.save(lotOrder);
        return true;
    }

    public List<LotOrder> getAllOrders() {
        List<LotOrder> lotOrders = orderRepository.findAll();
        return lotOrders;
    }
}
