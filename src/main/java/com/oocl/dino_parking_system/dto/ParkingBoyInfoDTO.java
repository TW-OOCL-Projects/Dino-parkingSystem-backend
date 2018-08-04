package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entitie.User;

public class ParkingBoyInfoDTO {
    private String nickname;
    private Long id;
    private String phone;
    private String email;
    private int lotNumber;
    private int carNumber;
    private int orderNumber;
    private int total;

    public ParkingBoyInfoDTO(User parkingBoy) {
        this.nickname = parkingBoy.getNickname();
        this.id = parkingBoy.getId();
        this.phone = parkingBoy.getPhone();
        this.email = parkingBoy.getEmail();
        this.lotNumber = parkingBoy.getParkingLots().size();
        this.carNumber = parkingBoy.getParkingLots().stream().mapToInt(parkingLot -> parkingLot.getCarNum()).sum();
        this.orderNumber = parkingBoy.getLotOrders().size();
        this.total = parkingBoy.getParkingLots().stream().mapToInt(parkingLot -> parkingLot.getSize()).sum();
    }

    public String getNickname() {
        return nickname;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getLotNumber() {
        return lotNumber;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public int getTotal() {
        return total;
    }

	public int getOrderNumber() {
		return orderNumber;
	}
}
