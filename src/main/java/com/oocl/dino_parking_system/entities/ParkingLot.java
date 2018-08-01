package com.oocl.dino_parking_system.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.oocl.dino_parking_system.constants.Constants.NORMAL;

@Table(name = "parking_lot")
@Entity
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int lot_size;
    private String status = NORMAL;

    @Transient
    private List<String> carsPlateNumber = new ArrayList<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "parking_boy_id")
    private Employee employee;

    public ParkingLot(String name, int lot_size) {
        this.name = name;
        this.lot_size = lot_size;
//        this.status = NORMAL;
    }



    public ParkingLot(String name, int lot_size, String status, List<String> carsPlateNumber, Employee employee) {
        this.name = name;
        this.lot_size = lot_size;
        //this.status = NORMAL;
        this.carsPlateNumber = carsPlateNumber;
        this.employee = employee;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<String> getCarsPlateNumber() {
        return carsPlateNumber;
    }

    public void setCarsPlateNumber(List<String> carsPlateNumber) {
        this.carsPlateNumber = carsPlateNumber;
    }
}
