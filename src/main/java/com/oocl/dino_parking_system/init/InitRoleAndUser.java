package com.oocl.dino_parking_system.init;

import com.oocl.dino_parking_system.entities.Role;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.RoleRepository;
import com.oocl.dino_parking_system.repositorys.UserRepository;
import com.oocl.dino_parking_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(value = 3)
public class InitRoleAndUser implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role(1L,"ROLE_ADMIN");
        Role manager = new Role(2L,"ROLE_MANAGER");
        Role parkingBoy = new Role(3L,"ROLE_PARKINGBOY");
        Role user = new Role(4L,"ROLE_USER");
        roleRepository.saveAll(Arrays.asList(admin,manager,parkingBoy,user));
        System.out.println("===============角色初始化完成=============");


        User user1 = new User("admin","管理员A","1234","110@qq.com","13160675789");
        user1.setRoles(Arrays.asList(admin));
        User user2 = new User("parkingboy","停车小弟A","1234","120@qq.com","13160675789");
        user2.setRoles(Arrays.asList(parkingBoy));
	    User user3 = new User("manager","停车经理A","1234","130@qq.com","13160675789");
	    user3.setRoles(Arrays.asList(manager));
        userRepository.saveAll(Arrays.asList(user1,user2,user3));
        System.out.println("===============用户初始化完成=============");
    }
}
