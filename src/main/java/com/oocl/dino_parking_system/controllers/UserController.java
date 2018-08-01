package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity createUser(@RequestBody User user) {
		if(userService.createUser(user)){
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@GetMapping("/users")
	public ResponseEntity getAllUser() {
		if(userService.getAllUser() != null){
			return new ResponseEntity<List<User>>(userService.getAllUser(),HttpStatus.OK);
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
		if(userService.updateUser(id, user) == 1){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else if(userService.updateUser(id, user) == 2) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}


