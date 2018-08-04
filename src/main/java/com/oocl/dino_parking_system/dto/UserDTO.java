package com.oocl.dino_parking_system.dto;

import com.oocl.dino_parking_system.entitie.User;

/**
 * Created by Vito Zhuang on 8/1/2018.
 */
public class UserDTO {
	private Long id;
	private String username;
	private String nickname;
	private String email;
	private String phone;
	private boolean status;

	public UserDTO(User user){
		this.id = user.getId();
		this.username = user.getUsername();
		this.nickname = user.getNickname();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.status = user.getStatus();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getNickname() {
		return nickname;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isStatus() {
		return status;
	}
}
