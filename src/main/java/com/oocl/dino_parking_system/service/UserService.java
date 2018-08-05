package com.oocl.dino_parking_system.service;

import com.oocl.dino_parking_system.dto.UserDTO;
import com.oocl.dino_parking_system.util.MD5Util;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.repository.UserRepository;
import com.oocl.dino_parking_system.util.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.oocl.dino_parking_system.constant.Constants.*;

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

	public String createUser(User user) {
		try {
			String password = UUID.randomUUID()
					.toString()
					.substring(0, 7);
			user.setPassword(new PasswordEncoder(SALT_STRING, "MD5")
					.encode(password)
					.substring(0, 7));
			userRepository.save(user);
			return password;
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> getAllUser() {
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	public int changeUserStatus(Long id, boolean status) {
		try {
			User one = userRepository.findById(id).get();
			if (one == null) return 2;
			one.setStatus(status);
			userRepository.save(one);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	public User getUserById(Long id) {
		try {
			User one = userRepository.findById(id).get();
			return one;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean updateUser(Long id, User user) {
		try {
			User one = userRepository.findById(id).get();
			one.setNickname(user.getNickname() != null ? user.getNickname() : one.getNickname());
			one.setUsername(user.getUsername() != null ? user.getUsername() : one.getUsername());
			one.setEmail(user.getEmail() != null ? user.getEmail() : one.getEmail());
			one.setPhone(user.getPhone() != null ? user.getPhone() : one.getPhone());
			userRepository.save(one);
			return true;
		} catch (Exception e) {
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

	public boolean changeWorkStatus(Long id, String workStatus) {
		try {
			User user = userRepository.findById(id).orElse(null);
			switch (workStatus) {
				case STATUS_ONDUTY:
				case STATUS_LATE:
				case STATUS_LEAVE:
					user.setWorkStatus(workStatus);
					userRepository.save(user);
					return true;
				case STATUS_OFFDUTY:
					if (user.getWorkStatus().equals(STATUS_ONDUTY)) {
						user.setWorkStatus(workStatus);
						userRepository.save(user);
						return true;
					} else {
						return false;
					}
				default:
					return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public List<UserDTO> findByConditions(String username, String nickname, Boolean status, String workStatus, String email, String phone) {
		List<User> users = userRepository.findAllByUsernameLikeAndNicknameLikeAndWorkStatusLikeAndEmailLikeAndPhoneLike("%"+username+"%","%"+nickname+"%","%"+workStatus+"%","%"+email+"%","%"+phone+"%");
		List<UserDTO> userDTOS = users.stream().map(UserDTO::new)
				.collect(Collectors.toList());
		if (status == null) {
			return users.stream().map(UserDTO::new)
					.collect(Collectors.toList());
		} else if (status) {
			return users.stream().filter(user -> user.getStatus()).map(UserDTO::new)
					.collect(Collectors.toList());
		} else {
			return users.stream().filter(user -> user.getStatus()==false).map(UserDTO::new)
					.collect(Collectors.toList());
		}

	}
}