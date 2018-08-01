package com.oocl.dino_parking_system.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/31/2018.
 */
@Entity
public class User implements UserDetails {


	public User() {
	}

	public User(String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User(Long id, String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String nickname;
	private String password;
	private String email;
	private String phone;
	private boolean status = true; // 注销状态：true为未注销
	@OneToMany(mappedBy = "user", fetch= FetchType.LAZY)
	private List<ParkingLot> parkingLots = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();

	public User(String username, String password, String email, String phone) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public User(String username, String nickname, String password, String email, String phone) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		List<Role> roles = this.getRoles();
		for (Role role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
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

	public List<ParkingLot> getParkingLots() {
		return parkingLots;
	}

	public void setParkingLots(List<ParkingLot> parkingLots) {
		this.parkingLots = parkingLots;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return this.status;
	}
}