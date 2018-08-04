package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.Role;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.RoleRepository;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
	UserRepository userRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

	public RoleService() {
	}

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public boolean setRoleToUser(Long id, String name) {
		User user = userRepository.findById(id).orElse(null);
		if(user!=null){
			Role role = roleRepository.findByName(name);
			if(role!=null){
				user.getRoles().add(role);
				userRepository.save(user);
				return true;
			}
			return false;
		}
		return false;
	}
}
