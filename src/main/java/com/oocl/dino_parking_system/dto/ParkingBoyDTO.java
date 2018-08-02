package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.User;

public class ParkingBoyDTO {
    private Long id;
    private String username;

    public ParkingBoyDTO(User parkingBoy) {
        this.id = parkingBoy.getId();
    }

    public ParkingBoyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
