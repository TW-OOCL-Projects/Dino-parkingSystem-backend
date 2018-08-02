package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 8/2/2018.
 */
@Service
public class ParkingBoyService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	ParkingLotsRepository parkingLotsRepository;

	public List<ParkingLotTinyDTO> findAllNotFullParkingLots(Long id) {
		try {
			User parkingBoy = userRepository.findById(id).get();
			return parkingBoy.getParkingLots().stream()
					.filter(parkingLot -> parkingLot.getSize() > parkingLot.getCarNum())
					.map(parkingLot -> new ParkingLotTinyDTO(parkingLot))
					.collect(Collectors.toList());
		} catch (Exception e) {
			return null;
		}
	}

	public User findParkingBoyById(Long parkingBoyId) {
		return userRepository.findById(parkingBoyId).get();
	}

	public boolean parCar(Long parkingBoyId, Long parkingLotId) {
		try {
			User parkingBoy = userRepository.findById(parkingBoyId).get();
			if (parkingBoyHasThisParkingLot(parkingBoy,parkingLotId)){
				ParkingLot parkingLot = parkingLotsRepository.findById(parkingLotId).get();
				parkingLot.setCarNum(parkingLot.getCarNum()-1);
				parkingLotsRepository.save(parkingLot);
				return true;
			}
			else{
				return false;
			}
		}catch (Exception e){
			return false;
		}
	}

	private boolean parkingBoyHasThisParkingLot(User parkingBoy, Long parkingLotId){
		try {
			for(ParkingLot parkingLot:parkingBoy.getParkingLots()){
				if(parkingLot.getId()==parkingLotId)
					return true;
			}
			return false;
		}catch (Exception e){
			return false;
		}
	}
}
