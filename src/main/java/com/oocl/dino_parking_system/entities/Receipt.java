package com.oocl.dino_parking_system.entities;

public class Receipt {
    private String id;
    private String status;

    public Receipt(String status) {
        this.id = Integer.toString(this.hashCode());
        this.status = status;
    }

    public Receipt(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
