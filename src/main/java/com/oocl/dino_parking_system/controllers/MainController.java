package com.oocl.dino_parking_system.controllers;

import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	UserService userService;

    @GetMapping(path = "/")
    public String index(){
        return "Hello,Dino!";
    }
	@GetMapping("/hello")
	public String heelo(){
		return "123";
	}

	@GetMapping("/users")
	public String getUsers() {
		return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
				"{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
	}

	@PostMapping("/signup")
	public boolean signup(@RequestBody User user){
    	return userService.save(user);
	}
}

