package com.oocl.dino_parking_system.services;

import com.oocl.dino_parking_system.entities.Role;
import com.oocl.dino_parking_system.repositorys.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Role save(Role role){
        return roleRepository.save(role);
    }

}
