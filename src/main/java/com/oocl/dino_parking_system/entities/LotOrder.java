package com.oocl.dino_parking_system.entities;

import javax.persistence.*;

@Table(name = "car_order")
@Entity
public class LotOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parking_boy_id")
    private User user;

    private String plateNumber;
    private String status;
    private String receiptId;

    public LotOrder(String type, String plateNumber, String status, String receiptId) {
        this.type = type;
        this.plateNumber = plateNumber;
        this.status = status;
        this.receiptId = receiptId;
    }

    public LotOrder(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
