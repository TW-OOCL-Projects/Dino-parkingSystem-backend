package com.oocl.dino_parking_system.controllers;

import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.entities.LotOrder;
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

import static com.oocl.dino_parking_system.constants.Constants.STATUS_WAITPARK;
import static com.oocl.dino_parking_system.constants.Constants.STATUS_WAITUNPARK;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ParkingBoyService parkingBoyService;

    // 返回所有（待处理）订单
    @Transactional
    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllOrders(@RequestParam(value="parkingBoyId",required=false)Long parkingBoyId){
    	if(parkingBoyId!=null){
		    List<OrderDTO> orders = orderService.findOrderByParkingBoyId(parkingBoyId).stream()
				    .filter(orderDTO -> orderDTO.getStatus().equals(STATUS_WAITPARK) || orderDTO.getStatus().equals(STATUS_WAITUNPARK))
				    .collect(Collectors.toList());
		    if(orders.size() > 0){
			    return new ResponseEntity(orders,HttpStatus.OK);
		    }else {
			    return ResponseEntity.notFound().build();
		    }
	    }
    	return new ResponseEntity(orderService.getAllOrders().stream()
			    .map(OrderDTO::new)
			    .collect(Collectors.toList()),HttpStatus.OK);
    }

    //根据status搜索所有订单
    @Transactional
    @GetMapping(path = "/{status}")
    public List<OrderDTO> getOrdersByStatus(@PathVariable String status){
        return orderService.getOrdersByStatus(status).stream()
		        .map(OrderDTO::new)
		        .collect(Collectors.toList());
    }

    // 小弟修改订单状态
    @Transactional
    @PutMapping(path = "/{orderId}")
    public ResponseEntity changeStatus(@PathVariable("orderId") Long orderId, @RequestBody JSONObject request){
		Long parkingBoyId = Long.valueOf(request.get("parkingBoyId").toString());
		String status = request.get("status").toString();
        User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
        if(parkingBoy!=null
		        && parkingBoyService.findAllNotFullParkingLots(parkingBoyId).size()>0){
		    if (orderService.changeOrderStatus(orderId, parkingBoy, status))
			    return ResponseEntity.status(HttpStatus.OK).build();
		    else
			    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }else{
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	    }
    }
}
