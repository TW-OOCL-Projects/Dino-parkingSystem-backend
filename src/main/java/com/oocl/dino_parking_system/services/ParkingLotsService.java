package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.dto.ParkingLotTinyDTO;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constants.Constants.FREEZE;
import static com.oocl.dino_parking_system.constants.Constants.NORMAL;

@Service
public class ParkingLotsService {
    @Autowired
    private ParkingLotsRepository parkingLotsRepository;

    public boolean createParkingLots(ParkingLot parkingLot) {
    	try {
		    parkingLotsRepository.save(parkingLot);
		    return true;
	    }catch (Exception e){
    		return false;
	    }
    }

    public List<ParkingLotTinyDTO> getAllParkingLots() {
       return parkingLotsRepository.findAll().stream()
		       .map(parkingLot -> new ParkingLotTinyDTO(parkingLot))
		       .collect(Collectors.toList());
    }

    public boolean updateParkingLots(Long id,ParkingLot parkingLot) {
        try{
            ParkingLot oldParkingLot = parkingLotsRepository.findById(id).get();
	        oldParkingLot.setName(parkingLot.getName());
	        oldParkingLot.setSize(parkingLot.getSize());
	        parkingLotsRepository.save(oldParkingLot);
	        return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }


    public boolean changeParkingLotStatus(long id) {
    	try {
		    ParkingLot parkingLot = parkingLotsRepository.findById(id).get();
		    if (parkingLot.getStatus().equals(NORMAL)) {
			    parkingLot.setStatus(FREEZE);
		    } else {
			    parkingLot.setStatus(NORMAL);
		    }
		    parkingLotsRepository.save(parkingLot);
		    return true;
	    }catch (NoSuchElementException e){
    		return false;
	    }
    }
}
