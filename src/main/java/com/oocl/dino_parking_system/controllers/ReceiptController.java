package com.oocl.dino_parking_system.controllers;

import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.Receipt;
import com.oocl.dino_parking_system.services.OrderService;
import com.oocl.dino_parking_system.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_WAITUNPARK;

@RestController
public class ReceiptController {

	@Autowired
    private OrderService orderService;
    @Autowired
	private ReceiptService receiptService;

    @Transactional
    @PostMapping("/receipts")
    public ResponseEntity park(@RequestBody JSONObject request) {
    	try {
		    Receipt receipt = receiptService.generateReceipt();
		    orderService.generateOrder(request.get("plateNum").toString(), receipt.getId());
		    return new ResponseEntity(receipt, HttpStatus.CREATED);
	    }catch (Exception e){
    		return ResponseEntity.badRequest().build();
	    }
    }

    @Transactional
	@PutMapping("/receipts/{receiptId}")
	public ResponseEntity unPark(@PathVariable String receiptId){
	    try {
		    LotOrder order = orderService.findOrderByReceiptId(receiptId);
		    orderService.changeOrderStatus(order.getId(), null, STATUS_WAITUNPARK);
		    return ResponseEntity.ok().build();
	    }catch (Exception e){
	    	return ResponseEntity.badRequest().build();
	    }
    }
}
