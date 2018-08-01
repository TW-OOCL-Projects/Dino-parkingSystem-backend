package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

//    public ParkingLot createParkingLots(ParkingLot parkingLot) {
//        return parkingLotsRepository.save(parkingLot);
//    }

    public boolean createParkingLots(ParkingLot parkingLot) {
        parkingLotsRepository.save(parkingLot);
        return true;
    }

    public List<ParkingLot> getAllParkingLots() {
       return parkingLotsRepository.findAll();
    }

    public int updateParkingLots(Long id,ParkingLot parkingLot) {
        try{
            ParkingLot one = parkingLotsRepository.findById(id).get();
            System.out.println(parkingLotsRepository.findById(id));
            if (one!=null){
                one.setName(parkingLot.getName());
                one.setLot_size(parkingLot.getLot_size());
                parkingLotsRepository.save(one);
                return 1;
            }else
            {
                return 0;
            }
        }catch (NoSuchElementException e){
            return 2;
        }

    }


    public boolean freezeParkingLots(long id) {
        ParkingLot parkingLot = parkingLotsRepository.findById(id).get();
        if (parkingLot.getStatus()== NORMAL ){
            parkingLot.setStatus(FREEZE);
        }else {
            parkingLot.setStatus(NORMAL);
        }
//        parkingLot.setStatus(status);
        parkingLotsRepository.save(parkingLot);
        return true;
    }
}
