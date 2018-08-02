package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.entities.Receipt;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
public class ReceiptController {

	@Autowired
    private OrderService orderService;
    @Autowired
	private ReceiptService receiptService;

    @Transactional
    @PostMapping("/receipts")
    public ResponseEntity park(@RequestBody String plateNumber) {
    	try {
		    Receipt receipt = receiptService.generateReceipt();
		    orderService.generateOrder(plateNumber, receipt.getId());
		    return new ResponseEntity(receipt, HttpStatus.CREATED);
	    }catch (Exception e){
    		return ResponseEntity.badRequest().build();
	    }
    }
}
