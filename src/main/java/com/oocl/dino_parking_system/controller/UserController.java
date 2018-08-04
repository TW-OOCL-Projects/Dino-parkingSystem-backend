package com.oocl.dino_parking_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.oocl.dino_parking_system.dto.UserDTO;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.service.RoleService;
import com.oocl.dino_parking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		String password = userService.createUser(user);
		if(password != null){
			return new ResponseEntity<>(password,HttpStatus.CREATED);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	public ResponseEntity getAllUser() {
		if(userService.getAllUser() != null){
			return new ResponseEntity<>(userService.getAllUser().stream()
					.map(UserDTO::new)
					.collect(Collectors.toList()),HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PatchMapping("/users/{id}")
	public ResponseEntity changeUserStatus(@PathVariable("id") Long id,@RequestBody boolean status) {
		if(userService.changeUserStatus(id,status) == 1){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else if(userService.changeUserStatus(id,status) == 2){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity getUserById(@PathVariable("id") Long id) {
		if(userService.getUserById(id) != null){
			return new ResponseEntity<User>(userService.getUserById(id),HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity updateUser(@PathVariable("id") Long id,@RequestBody User user) {
		if(userService.updateUser(id, user)){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	//用户授权
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/users/{id}/roles")
	public ResponseEntity grantUser(@PathVariable("id") Long id,
	                                @RequestBody JSONObject req){
		if(roleService.setRoleToUser(id,req.get("role").toString())){
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.badRequest().build();
		}
	}

	// 修改用户的工作状态
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
	@PutMapping(path = "/users/{id}/workStatus")
	public ResponseEntity changeWorkStatus(@PathVariable Long id,
	                                       @RequestBody JSONObject req){
		if(userService.changeWorkStatus(id,req.get("workStatus").toString())){
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.badRequest().build();
		}
	}
}


