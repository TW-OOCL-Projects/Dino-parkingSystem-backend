package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.entities.Receipt;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiptController {

    private OrderService orderService;
    private ReceiptService receiptService;

    @Autowired
    public ReceiptController(OrderService orderService, ReceiptService receiptService) {
        this.orderService = orderService;
        this.receiptService = receiptService;
    }

    @PostMapping("/receipts")
    public Receipt park(@RequestBody String plateNumber) {
        Receipt receipt = receiptService.generateReceipt();
        orderService.generateOrder(plateNumber, receipt.getId());
        return receipt;
    }
}
