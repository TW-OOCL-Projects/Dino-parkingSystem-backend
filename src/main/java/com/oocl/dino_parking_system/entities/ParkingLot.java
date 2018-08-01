package com.oocl.dino_parking_system.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import static com.oocl.dino_parking_system.constants.Constants.NORMAL;

@Table(name = "parking_lot")
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int size;
    private String status = NORMAL;// 停车场开放状态：默认true开放

    @Transient
    private Map<String,String> cars = new HashMap<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parking_boy_id")
    @JsonIgnore
    private User parkingBoy;

	public ParkingLot() {
	}

	public ParkingLot(String name, int size) {
		this.name = name;
		this.size = size;
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
