package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ParkingLotsRepository extends JpaRepository<ParkingLot,Long> {
	List<ParkingLot> findByName(String name); // 精确查询
	List<ParkingLot> findAllBySizeGreaterThanEqual(Integer size); // 大于等于查询
	List<ParkingLot> findAllBySizeLessThanEqual(Integer size); // 小于等于查询
	List<ParkingLot> findAllBySizeBetween(Integer left, Integer right); // 大于等于小于等于查询
}
