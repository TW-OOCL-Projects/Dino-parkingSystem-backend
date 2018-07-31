package com.oocl.dino_parking_system.controller;

import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.repository.RoleRepository;
import com.oocl.dino_parking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
    RoleRepository roleRepository;

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

	@GetMapping("/roles")
	public List<Role> findAllRole(){
        return roleRepository.findAll();
    }
}

