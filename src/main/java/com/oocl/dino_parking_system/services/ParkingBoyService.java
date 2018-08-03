package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.dto.OrderDTO;
import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constants.Constants.*;

/**
 * Created by Vito Zhuang on 8/2/2018.
 */
@Service
public class ParkingBoyService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ParkingLotsRepository parkingLotsRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderService orderService;



	public List<ParkingLotTinyDTO> findAllNotFullParkingLots(Long id) {
		try {
			User parkingBoy = userRepository.findById(id).orElse(null);
			return parkingBoy.getParkingLots().stream()
					.filter(parkingLot -> parkingLot.getSize() > parkingLot.getCarNum()
							&& parkingLot.getStatus().equals(STATUS_NORMAL))
					.map(ParkingLotTinyDTO::new)
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}


    public User findParkingBoyById(Long parkingBoyId) {
        Optional<User> optional = userRepository.findById(parkingBoyId);
        return optional.orElse(null);
    }

	public boolean parCar(Long parkingBoyId, Long parkingLotId, Long orderId) {
		try {
			User parkingBoy = userRepository.findById(parkingBoyId).orElse(null);
			if (parkingBoyHasThisParkingLot(parkingBoy,parkingLotId)){
				ParkingLot parkingLot = parkingLotsRepository.findById(parkingLotId).orElse(null);
				parkingLot.setCarNum(parkingLot.getCarNum()+1);
				parkingLotsRepository.save(parkingLot);
				return orderService.changeOrderStatus(orderId,parkingBoy,STATUS_PARKED);
			}
			else{
				return false;
			}
		}catch (Exception e){
			return false;
		}
	}

    private boolean parkingBoyHasThisParkingLot(User parkingBoy, Long parkingLotId) {
        try {
            for (ParkingLot parkingLot : parkingBoy.getParkingLots()) {
                if (parkingLot.getId() == parkingLotId)
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> findAllParkingBoys() {
        List<User> parkingBoys = userRepository.findAll().stream().
                filter(parkingBoy -> parkingBoy.getRoles().stream().anyMatch(role -> role.getName().equals(ROLE_PARKINGBOY))).
                collect(Collectors.toList());
        System.out.println(userRepository.findAll());
        return parkingBoys;
    }

	public List<OrderDTO> findAllFinishOrderByParkingBoyId(Long parkingBoyId) {
		User parkingBoy = userRepository.findById(parkingBoyId).orElse(null);
		if(parkingBoy!=null){
			return parkingBoy.getLotOrders().stream()
					.filter(lotOrder -> lotOrder.getStatus().equals(STATUS_FINISH))
					.map(OrderDTO::new)
					.collect(Collectors.toList());
		}
		return new ArrayList<>();
	}
}
