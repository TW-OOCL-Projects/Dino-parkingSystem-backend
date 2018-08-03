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
		LotOrder lotOrder = new LotOrder(TYPE_PARKCAR, plateNumber, STATUS_NOROB, receiptId);
		return orderRepository.save(lotOrder);
	}

	public boolean generateOrder(LotOrder lotOrder) {
		lotOrder.setStatus(STATUS_NOROB);
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

	public boolean changeOrderStatus(Long orderId, User parkingBoy) {
		try {
			LotOrder order = orderRepository.findById(orderId).orElse(null);
			switch (order.getStatus()) {
				case STATUS_NOROB:
					order.setStatus(STATUS_WAITPARK);// 等待停车
					order.setType(TYPE_PARKCAR);// 存车订单
					order.setParkingBoy(parkingBoy);
					parkingBoy.addOrder(order);
					System.out.println(order.getStatus());
					return true;
				case STATUS_WAITPARK:
					if(checkBoyPermisson(parkingBoy,order)) {
						order.setStatus(STATUS_PARKED);// 停车成功
						System.out.println(order.getStatus());
						return true;
					}else{
						return false;
					}
				case STATUS_PARKED:
					order.setStatus(STATUS_WAITUNPARK);// 等待取车
					order.setType(TYPE_PARKOUTCAR); // 取车订单
					System.out.println(order.getStatus());
					return true;
				case STATUS_WAITUNPARK:
					if(checkBoyPermisson(parkingBoy,order)) {
						order.setStatus(STATUS_FINISH);// 取车完成
						System.out.println(order.getStatus());
						return true;
					}
					else{
						return false;
					}
				default:
					return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkBoyPermisson(User parkingBoy, LotOrder order) {
		return order.getParkingBoy().getId().equals(parkingBoy.getId());
	}

	public List<OrderDTO> findOrderByParkingBoyId(String type, Long parkingBoyId) {
		User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
		if (parkingBoy != null) {
			return parkingBoy.getLotOrders().stream()
					.filter(lotOrder -> lotOrder.getType().equals(type))
					.map(OrderDTO::new)
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}
}
