package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.ParkingLot;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class ParkingLotsRepositoryTest {
    @Autowired
    private ParkingLotsRepository parkingLotsRepository;
    @Autowired
    private TestEntityManager manager;


    @Test
    public void findAll() {
        //given
       manager.persistAndFlush(new ParkingLot("停车场A",20));
        //when
        List<ParkingLot> parkingLotList = parkingLotsRepository.findAll();
        //then
        assertThat(parkingLotList.size(),is(1));
    }

    @Test
    public void findAllBySizeGreaterThanEqual() {
        //given
        manager.persist(new ParkingLot("停车场A",20));
        manager.persist(new ParkingLot("停车场B",5));
        //when
        System.out.println(parkingLotsRepository.findAll());
        List<ParkingLot> parkingLotList = parkingLotsRepository.findAllBySizeGreaterThanEqual(10);
        //then
        assertThat(parkingLotList.size(),is(1));
        assertThat(parkingLotList.get(0).getName(),is("停车场A"));
        assertThat(parkingLotList.get(0).getSize(),is(20));
    }

    @Test
    public void findAllBySizeLessThanEqual() {
    }

    @Test
    public void findAllBySizeBetween() {
    }
}