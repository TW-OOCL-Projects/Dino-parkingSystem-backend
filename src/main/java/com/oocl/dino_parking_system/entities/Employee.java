package com.oocl.dino_parking_system.entities;

import javax.persistence.*;
import java.util.List;

@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String status;
    private String authority;

    @OneToMany(mappedBy = "employee", fetch= FetchType.LAZY)
    private List<ParkingLot> parkingLots;

    public Employee(String name, String email, String phone, String status, String authority) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.authority = authority;
    }

    public Employee() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
