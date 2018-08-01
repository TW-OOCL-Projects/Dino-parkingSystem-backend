package com.oocl.dino_parking_system.entities;

import javax.persistence.*;
import java.util.List;

@Table(name = "parking_lot")
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int lot_size;
    private String status;

    @Transient
    private List<String> carsPlateNumber;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parking_boy_id")
    private User user;

    public ParkingLot(String name, int lot_size, String status, List<String> carsPlateNumber, User user) {
        this.name = name;
        this.lot_size = lot_size;
        this.status = status;
        this.carsPlateNumber = carsPlateNumber;
        this.user = user;
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

    public int getLot_size() {
        return lot_size;
    }

    public void setLot_size(int lot_size) {
        this.lot_size = lot_size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getCarsPlateNumber() {
        return carsPlateNumber;
    }

    public void setCarsPlateNumber(List<String> carsPlateNumber) {
        this.carsPlateNumber = carsPlateNumber;
    }
}
