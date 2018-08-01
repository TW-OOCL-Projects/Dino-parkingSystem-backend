package com.oocl.dino_parking_system.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Table(name = "parking_lot")
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;
    private boolean status = true;// 停车场开放状态：默认true开放

    @Transient
    private Map<String,String> cars = new HashMap<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parking_boy_id")
    private User parkingBoy;

    public ParkingLot(String name, int lot_size, User parkingBoy) {
        this.name = name;
        this.size = lot_size;
        this.parkingBoy = parkingBoy;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getParkingBoy() {
        return parkingBoy;
    }

    public void setParkingBoy(User parkingBoy) {
        this.parkingBoy = parkingBoy;
    }

	public Map<String, String> getCars() {
		return cars;
	}

	public void setCars(Map<String, String> cars) {
		this.cars = cars;
	}
}
