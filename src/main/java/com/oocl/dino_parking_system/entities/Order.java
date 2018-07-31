package com.oocl.dino_parking_system.entities;

import javax.persistence.*;

@Table(name = "order")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee parkingBoy;

    private String plateNumber;
    private String status;

    public Order(String type, Employee parkingBoy, String plateNumber, String status) {
        this.type = type;
        this.parkingBoy = parkingBoy;
        this.plateNumber = plateNumber;
        this.status = status;
    }

    public Order(){}

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

    public Employee getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(Employee parkingBoy) {
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
}
