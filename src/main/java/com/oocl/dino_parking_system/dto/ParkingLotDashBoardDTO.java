package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.ParkingLot;

/**
 * Created by Vito Zhuang on 8/1/2018.
 */
public class ParkingLotDashBoardDTO {
	private Long parkingLotId;
	private String parkingLotName;
	private int size;
	private int carNum;
	private Long parkingBoyId;
	private String parkingBoyName;

	public ParkingLotDashBoardDTO(ParkingLot parkingLot){
		this.parkingLotId = parkingLot.getId();
		this.parkingLotName = parkingLot.getName();
		this.size = parkingLot.getSize();
		this.carNum = parkingLot.getSize()-parkingLot.getCars().size();
		this.parkingBoyId = parkingLot.getParkingBoy()!=null?parkingLot.getParkingBoy().getId():0;
		this.parkingBoyName = parkingLot.getParkingBoy()!=null?parkingLot.getParkingBoy().getNickname():null;
	}

	public Long getParkingLotId() {
		return parkingLotId;
	}

	public String getParkingLotName() {
		return parkingLotName;
	}

	public int getSize() {
		return size;
	}

	public int getCarNum() {
		return carNum;
	}

	public Long getParkingBoyId() {
		return parkingBoyId;
	}

	public String getParkingBoyName() {
		return parkingBoyName;
	}
}
