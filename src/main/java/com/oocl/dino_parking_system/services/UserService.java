package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.constants.MD5Util;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserService() {
	}

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByUsername(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return user;
	}


	public boolean validate(User user) {
		User entity = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (entity != null) {
			return true;
		}
		return false;
	}

	public boolean createUser(User user) {
		try{
			user.setPassword(MD5Util.encode(user.getPassword()));
			userRepository.save(user);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	public List<User> getAllUser() {
		try{
			return userRepository.findAll();
		}catch (Exception e){
			return null;
		}
	}

	public int changeUserStatus(Long id, boolean status) {
		try{
			User one = userRepository.findById(id).get();
			if(one == null) return 2;
			one.setStatus(status);
			userRepository.save(one);
			return 1;
		}catch (Exception e){
			return 0;
		}
	}

	public User getUserById(Long id) {
		try{
			User one = userRepository.findById(id).get();
			return one;
		}catch (Exception e){
			return null;
		}
	}

	public boolean updateUser(Long id, User user) {
		try{
			User one = userRepository.findById(id).get();
			one.setNickname(user.getNickname()!=null?user.getNickname():one.getNickname());
			one.setUsername(user.getUsername()!=null?user.getUsername():one.getUsername());
			one.setEmail(user.getEmail()!=null?user.getEmail():one.getEmail());
			one.setPhone(user.getPhone()!=null?user.getPhone():one.getPhone());
			userRepository.save(one);
			return true;
		}catch (Exception e){
			return false;
		}
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