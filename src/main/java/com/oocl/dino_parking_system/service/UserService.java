package com.oocl.dino_parking_system.service;

import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		System.out.println("loadUser:"+user.getUsername()+user.getAuthorities());
		return user;
	}


	public boolean validate(User user) {
		User entity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (entity != null) {
			return true;
		}
		return false;
	}

	public boolean save(User user) {
		userRepository.save(user);
		return true;
	}

	public boolean delete(Long id) {
		userRepository.deleteById(id);
		return true;
	}

	public boolean edit(User user) {
		userRepository.save(user);
		return true;
	}

	public boolean exits(User user) {
		User entity = userRepository.findByUsername(user.getUsername());
		if (entity != null) {
			return true;
		}
		return false;
	}
}