package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.dto.ParkingLotDashBoardDTO;
import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constants.Constants.STATUS_FREEZE;
import static com.oocl.dino_parking_system.constants.Constants.STATUS_NORMAL;

@Service
public class ParkingLotsService {
	@Autowired
	private ParkingLotsRepository parkingLotsRepository;

	public boolean createParkingLots(ParkingLot parkingLot) {
		try {
			parkingLotsRepository.save(parkingLot);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ParkingLotTinyDTO> getAllParkingLots() {
		return parkingLotsRepository.findAll().stream()
				.map(ParkingLotTinyDTO::new)
				.collect(Collectors.toList());
	}

	public boolean updateParkingLots(Long id, ParkingLot parkingLot) {
		try {
			ParkingLot oldParkingLot = parkingLotsRepository.findById(id).orElse(null);
			oldParkingLot.setName(parkingLot.getName()!=null?parkingLot.getName():oldParkingLot.getName());
			oldParkingLot.setSize(parkingLot.getSize()!=0?parkingLot.getSize():oldParkingLot.getSize());
			parkingLotsRepository.save(oldParkingLot);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public boolean changeParkingLotStatus(long id) {
		try {
			ParkingLot parkingLot = parkingLotsRepository.findById(id).orElse(null);
			if (parkingLot.isStatus()==(STATUS_NORMAL)) {
				parkingLot.setStatus(STATUS_FREEZE);
			} else {
				parkingLot.setStatus(STATUS_NORMAL);
			}
			parkingLotsRepository.save(parkingLot);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ParkingLotDashBoardDTO> findAllParkingLotDashBoard() {
		return parkingLotsRepository.findAll().stream()
				.map(ParkingLotDashBoardDTO::new)
				.collect(Collectors.toList());
	}

	public List<ParkingLotDashBoardDTO> findAllParkingLotDashBoardByPaging(PageRequest pageRequest) {
		return parkingLotsRepository.findAll(pageRequest).stream()
				.map(ParkingLotDashBoardDTO::new)
				.collect(Collectors.toList());
	}

	public List<ParkingLotTinyDTO> findAllParkingLotsByName(String name){
		return parkingLotsRepository.findByName(name).stream()
				.map(ParkingLotTinyDTO::new)
				.collect(Collectors.toList());
	}

	public List<ParkingLotTinyDTO> findAllParkingLotsBySizeGreaterThanEqual(Integer size){
		return parkingLotsRepository.findAllBySizeGreaterThanEqual(size).stream()
				.map(ParkingLotTinyDTO::new)
				.collect(Collectors.toList());
	}
	public List<ParkingLotTinyDTO> findAllParkingLotsBySizeLessThanEqual(Integer size){
		return parkingLotsRepository.findAllBySizeLessThanEqual(size).stream()
				.map(ParkingLotTinyDTO::new)
				.collect(Collectors.toList());
	}
	public List<ParkingLotTinyDTO> findAllParkingLotsBySizeBetween(Integer left, Integer right){
		return parkingLotsRepository.findAllBySizeBetween(left,right).stream()
				.map(ParkingLotTinyDTO::new)
				.collect(Collectors.toList());
	}

	public ParkingLotTinyDTO findParkingLotById(Long id) {
		ParkingLot parkingLot = parkingLotsRepository.findById(id).orElse(null);
		return parkingLot!=null?new ParkingLotTinyDTO(parkingLot):null;
	}
}
