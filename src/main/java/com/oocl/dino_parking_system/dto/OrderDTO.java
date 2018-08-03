package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.LotOrder;

import java.time.format.DateTimeFormatter;

public class OrderDTO {
    private Long id;
    private String type;
    private ParkingBoyTinyDTO parkingBoy;
    private String plateNumber;
    private String status;
    private String receiptId;
    private String parkDate;
    private String unParkDate;

    public OrderDTO() {
    }

    public OrderDTO(LotOrder order) {
        this.id = order.getId();
        this.type = order.getType();
        this.parkingBoy = order.getParkingBoy()!=null?new ParkingBoyTinyDTO(order.getParkingBoy()):null;
        this.plateNumber = order.getPlateNumber();
        this.status = order.getStatus();
        this.receiptId = order.getReceiptId();
        this.parkDate = DateTimeFormatter.ofPattern("hh:mm - yyyy/MM/dd").format(order.getParkDate());
        this.unParkDate =order.getUnParkDate()!=null
		        ?DateTimeFormatter.ofPattern("hh:mm - yyyy/MM/dd").format(order.getUnParkDate())
                :null;
    }

	public Long getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public ParkingBoyTinyDTO getParkingBoy() {
		return parkingBoy;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public String getParkDate() {
		return parkDate;
	}

	public String getUnParkDate() {
		return unParkDate;
	}
}
