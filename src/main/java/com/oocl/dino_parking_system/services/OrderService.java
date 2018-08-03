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

	public boolean changeOrderStatus(Long orderId, User parkingBoy, String status) {
		try {
			LotOrder order = orderRepository.findById(orderId).orElse(null);
			switch (status) {
				case STATUS_WAITPARK:
					order.setStatus(STATUS_WAITPARK);// 等待停车
					order.setType(TYPE_PARKCAR);// 存车订单
					order.setParkingBoy(parkingBoy);
					parkingBoy.addOrder(order);
					orderRepository.save(order);
					return true;
				case STATUS_PARKED:
					if(checkBoyPermisson(parkingBoy,order)) {
						order.setStatus(STATUS_PARKED);// 停车成功
						orderRepository.save(order);
						return true;
					}else{
						return false;
					}
				case STATUS_WAITUNPARK:
					order.setStatus(STATUS_WAITUNPARK);// 等待取车
					order.setType(TYPE_PARKOUTCAR); // 取车订单
					orderRepository.save(order);
					return true;
				case STATUS_FINISH:
					if(checkBoyPermisson(parkingBoy,order)) {
						order.setStatus(STATUS_FINISH);// 取车完成
						orderRepository.save(order);
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
		if(order!=null && order.getParkingBoy()!=null)
			return order.getParkingBoy().getId().equals(parkingBoy.getId());
		else
			return false;
	}

	public List<OrderDTO> findOrderByParkingBoyId(Long parkingBoyId) {
		User parkingBoy = parkingBoyService.findParkingBoyById(parkingBoyId);
		if (parkingBoy != null) {
			return parkingBoy.getLotOrders().stream()
					.map(OrderDTO::new)
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}
}
