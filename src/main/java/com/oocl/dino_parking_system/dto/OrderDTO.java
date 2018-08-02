package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.User;

public class OrderDTO {
    private Long id;
    private String type;
    private User parkingBoy;
    private String plateNumber;
    private String status;
    private String receiptId;

    public OrderDTO() {
    }

    public OrderDTO(LotOrder order) {
        this.id = order.getId();
        this.type = order.getType();
        this.parkingBoy = order.getParkingBoy();
        this.plateNumber = order.getPlateNumber();
        this.status = order.getStatus();
        this.receiptId = order.getReceiptId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(User parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
}
