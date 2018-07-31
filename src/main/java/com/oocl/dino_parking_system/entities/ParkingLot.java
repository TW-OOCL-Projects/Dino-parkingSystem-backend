package com.oocl.dino_parking_system.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "parking_lot")
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;
    private String status;

    private List<String> carsPlateNumber = new ArrayList<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee parkingBoy;

    public ParkingLot(String name, int size, String status) {
        this.name = name;
        this.size = size;
        this.status = status;
    }

    public ParkingLot(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(Employee parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

    public List<String> getCarsPlateNumber() {
        return carsPlateNumber;
    }

    public void setCarsPlateNumber(List<String> carsPlateNumber) {
        this.carsPlateNumber = carsPlateNumber;
    }
}
