package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constants.Constants.*;

@Component
public class OrderService {



    @Autowired
    private ParkingBoyService parkingBoyService;

    @Autowired
    private OrderRepository orderRepository;

    public LotOrder generateOrder(String plateNumber, String receiptId) {
        LotOrder lotOrder = new LotOrder(TYPE_PARKCAR, plateNumber, STATUS_NOHANDLE, receiptId);
        return orderRepository.save(lotOrder);
    }

    public boolean generateOrder(LotOrder lotOrder) {
        lotOrder.setStatus(STATUS_NOHANDLE);
        lotOrder.setType(TYPE_PARKCAR);
        orderRepository.save(lotOrder);
        return true;
    }

    public List<LotOrder> getAllOrders() {
        List<LotOrder> lotOrders = orderRepository.findAll();

        return lotOrders;
    }


    public List<LotOrder> getOrdersByStatus(String status) {
        List<LotOrder> lotOrders = orderRepository.findByStatus(status);
        return lotOrders;
    }

    public Boolean changeStatus(Long id, User parkingBoy) {
    	try {
		    Boolean key = true;
		    LotOrder lotOrder = orderRepository.findById(id).get();

		    if (lotOrder.getStatus().equals(STATUS_HANDLE)) {
			    key = false;
		    } else {
			    lotOrder.setStatus(STATUS_HANDLE);
			    lotOrder.setType(TYPE_PARKOUTCAR);
			    lotOrder.setParkingBoy(parkingBoy);
			    parkingBoy.addOrder(lotOrder);
		    }
		    return key;
	    }catch (Exception e){
    		return false;
	    }
    }

    public List<OrderDTO> findOrderByParkingBoyId(String type, Long parkingBoyId) {
        User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
        if(parkingBoy!=null) {
            return parkingBoy.getLotOrders().stream()
                    .filter(lotOrder -> lotOrder.getType().equals(type))
                    .map(OrderDTO::new)
                    .collect(Collectors.toList());
        }else {
            return null;
        }
    }
}
