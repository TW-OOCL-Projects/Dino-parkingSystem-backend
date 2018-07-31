package com.oocl.dino_parking_system.service;

import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

}
