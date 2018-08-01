package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.ParkingLot;

/**
 * Created by Vito Zhuang on 8/1/2018.
 */
public class ParkingLotTinyDTO {
	private Long id;
	private String name;
	private int size;

	public ParkingLotTinyDTO(ParkingLot parkingLot){
		this.id = parkingLot.getId();
		this.name = parkingLot.getName();
		this.size = parkingLot.getSize();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}
}
