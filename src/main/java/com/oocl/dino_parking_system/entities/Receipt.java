package com.oocl.dino_parking_system.entities;

public class Receipt {
    private int id;
    private String status;

    public Receipt(String status) {
        this.id = this.hashCode();
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
