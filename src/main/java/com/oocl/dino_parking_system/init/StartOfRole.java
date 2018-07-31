package com.oocl.dino_parking_system.init;

import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
public class StartOfRole implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role(1L,"ROLE_ADMIN"));
        roleRepository.save(new Role(2L,"ROLE_MANAGER"));
        roleRepository.save(new Role(3L,"ROLE_PARKINGBOY"));
        roleRepository.save(new Role(4L,"ROLE_USER"));
        System.out.println("===============角色初始化完成=============");
    }
}
