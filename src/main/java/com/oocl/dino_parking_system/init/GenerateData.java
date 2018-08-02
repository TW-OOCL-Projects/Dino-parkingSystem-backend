package com.oocl.dino_parking_system.init;

import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.ParkingLot;
import com.oocl.dino_parking_system.entities.Role;
import com.oocl.dino_parking_system.entities.User;
import com.oocl.dino_parking_system.repositorys.OrderRepository;
import com.oocl.dino_parking_system.repositorys.ParkingLotsRepository;
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
public class GenerateData implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingLotsRepository parkingLotsRepository;

    @Autowired
	private OrderRepository orderRepository;

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
        User user2 = new User("parkingboy1","停车小弟A","1234","120@qq.com","13160675789");
        user2.setRoles(Arrays.asList(parkingBoy));
	    User user3 = new User("manager","停车经理A","1234","130@qq.com","13160675789");
	    user3.setRoles(Arrays.asList(manager));

	    User user4 = new User("parkingboy2","停车小弟B","1234","121@qq.com","13160675789");
	    user4.setRoles(Arrays.asList(parkingBoy));
	    User user5 = new User("parkingboy3","停车小弟C","1234","122@qq.com","13160675789");
	    user5.setRoles(Arrays.asList(parkingBoy));
	    User user6 = new User("parkingboy4","停车小弟D","1234","123@qq.com","13160675789");
	    user6.setRoles(Arrays.asList(parkingBoy));
	    User user7 = new User("parkingboy5","停车小弟E","1234","124@qq.com","13160675789");
	    user7.setRoles(Arrays.asList(parkingBoy));

        userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5,user6,user7));
        System.out.println("===============用户初始化完成=============");

	    ParkingLot parkingLot1 = new ParkingLot("oocl停车场1",20);
	    parkingLot1.setParkingBoy(user2);

	    parkingLot1.setCarNum(13);
	    ParkingLot parkingLot2 = new ParkingLot("oocl停车场2",10);
	    parkingLot2.setParkingBoy(user2);
	    parkingLot2.setCarNum(10);
	    ParkingLot parkingLot3 = new ParkingLot("oocl停车场3",20);
	    parkingLot3.setParkingBoy(user4);
	    parkingLot3.setCarNum(1);
	    ParkingLot parkingLot4 = new ParkingLot("oocl停车场4",25);
	    parkingLot4.setParkingBoy(user4);
	    parkingLot4.setCarNum(0);
	    ParkingLot parkingLot5 = new ParkingLot("oocl停车场5",20);
	    parkingLot5.setParkingBoy(user5);
	    parkingLot5.setCarNum(19);
	    ParkingLot parkingLot6 = new ParkingLot("oocl停车场6",14);
	    parkingLot6.setParkingBoy(user6);
	    parkingLot6.setCarNum(11);
	    ParkingLot parkingLot7 = new ParkingLot("oocl停车场7",20);
	    parkingLot7.setParkingBoy(user7);
	    parkingLot7.setCarNum(3);
	    parkingLotsRepository.saveAll(Arrays.asList(parkingLot1,parkingLot2,parkingLot3,
			    parkingLot4,parkingLot5,parkingLot6,parkingLot7));

	    /*
	    * 订单初始化数据
	    *
	    * */
		LotOrder order1 = new LotOrder("粤DHC9767","1");
		LotOrder order2 = new LotOrder("粤DH76647","2");
		orderRepository.saveAll(Arrays.asList(order1,order2));
    }
}
