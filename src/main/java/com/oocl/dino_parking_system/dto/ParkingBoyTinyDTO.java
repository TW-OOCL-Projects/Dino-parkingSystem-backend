package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entities.User;

public class ParkingBoyTinyDTO {
    private Long id;
    private String nickname;
    private String username;

    public ParkingBoyTinyDTO(User parkingBoy) {
        this.id = parkingBoy.getId();
        this.nickname = parkingBoy.getNickname();
        this.username = parkingBoy.getUsername();
    }

    public ParkingBoyTinyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getNickname() {
		return nickname;
	}
}
