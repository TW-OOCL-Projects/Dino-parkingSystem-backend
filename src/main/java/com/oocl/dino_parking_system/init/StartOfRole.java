package com.oocl.dino_parking_system.init;

import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.repository.RoleRepository;
import com.oocl.dino_parking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(value = 2)
public class StartOfRole implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role(1L,"ROLE_ADMIN");
        Role manager = new Role(2L,"ROLE_MANAGER");
        Role parkingBoy = new Role(3L,"ROLE_PARKINGBOY");
        Role user = new Role(4L,"ROLE_USER");
        roleRepository.saveAll(Arrays.asList(admin,manager,parkingBoy,user));
        System.out.println("===============角色初始化完成=============");


        User user1 = new User(1L,"vito","1234");
        user1.setRoles(Arrays.asList(admin,manager));
        User user2 = new User(2L,"zojian","1234");
        user2.setRoles(Arrays.asList(parkingBoy));
        userRepository.saveAll(Arrays.asList(user1,user2));
        System.out.println("===============用户初始化完成=============");
    }
}
