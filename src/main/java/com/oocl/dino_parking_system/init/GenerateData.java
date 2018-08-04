package com.oocl.dino_parking_system.init;

import com.oocl.dino_parking_system.entitie.LotOrder;
import com.oocl.dino_parking_system.entitie.ParkingLot;
import com.oocl.dino_parking_system.entitie.Role;
import com.oocl.dino_parking_system.entitie.User;
import com.oocl.dino_parking_system.repository.OrderRepository;
import com.oocl.dino_parking_system.repository.ParkingLotsRepository;
import com.oocl.dino_parking_system.repository.RoleRepository;
import com.oocl.dino_parking_system.repository.UserRepository;
import com.oocl.dino_parking_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.oocl.dino_parking_system.constant.Constants.*;

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
        /*
         * 角色初始化数据
         *
         * */
        Role admin = new Role(1L,ROLE_ADMIN);
        Role manager = new Role(2L,ROLE_MANAGER);
        Role parkingBoy = new Role(3L,ROLE_PARKINGBOY);
        roleRepository.saveAll(Arrays.asList(admin,manager,parkingBoy));
        System.out.println("===============角色初始化完成=============");
        /*
         * 员工初始化数据
         *
         * */

        User user1 = new User("admin","管理员A","bb3a4f6","110@qq.com","13160675789");
        user1.setRoles(Arrays.asList(admin));
        User user2 = new User("parkingboy1","停车小弟A","bb3a4f6","120@qq.com","13160675789");
        user2.setRoles(Arrays.asList(parkingBoy));
	    User user3 = new User("manager","停车经理A","bb3a4f6","130@qq.com","13160675789");
	    user3.setRoles(Arrays.asList(manager));
	    User user4 = new User("parkingboy2","停车小弟B","bb3a4f6","121@qq.com","13160675789");
	    user4.setRoles(Arrays.asList(parkingBoy));
	    User user5 = new User("parkingboy3","停车小弟C","bb3a4f6","122@qq.com","13160675789");
	    user5.setRoles(Arrays.asList(parkingBoy));
	    User user6 = new User("parkingboy4","停车小弟D","bb3a4f6","123@qq.com","13160675789");
	    user6.setRoles(Arrays.asList(parkingBoy));
	    User user7 = new User("parkingboy5","停车小弟E","bb3a4f6","124@qq.com","13160675789");
	    user7.setRoles(Arrays.asList(parkingBoy));

        userRepository.saveAll(Arrays.asList(user1,user2,user3,user4,user5,user6,user7));
        System.out.println("===============用户初始化完成=============");

        /*
         * 停车场初始化数据
         *
         * */
	    ParkingLot parkingLot1 = new ParkingLot("oocl停车场1",20);
	    parkingLot1.setParkingBoy(user2);

	    parkingLot1.setCarNum(13);
	    ParkingLot parkingLot2 = new ParkingLot("oocl停车场2",10);
	    parkingLot2.setParkingBoy(user3);
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
	    ParkingLot parkingLot7 = new ParkingLot("oocl停车场7",4);
	    parkingLot7.setParkingBoy(user7);
	    parkingLot7.setCarNum(3);
	    ParkingLot parkingLot8 = new ParkingLot("oocl停车场8",10);
	    ParkingLot parkingLot9 = new ParkingLot("oocl停车场9",10);
	    ParkingLot parkingLot10 = new ParkingLot("oocl停车场10",10);
	    parkingLotsRepository.saveAll(Arrays.asList(parkingLot1,parkingLot2,parkingLot3,parkingLot4,
			    parkingLot5,parkingLot6,parkingLot7,parkingLot8,parkingLot9,parkingLot10));
        System.out.println("===============停车场初始化完成=============");
	    /*
	    * 订单初始化数据
	    *
	    * */
		LotOrder order1 = new LotOrder("粤DHC9767","1");
		LotOrder order2 = new LotOrder("粤C76647","2");
		LotOrder order3 = new LotOrder("粤C4767","3");
		LotOrder order4 = new LotOrder("粤CH73647","4");
		LotOrder order5 = new LotOrder("粤AHC2767","5");
		LotOrder order6 = new LotOrder("粤VH71647","6");
		LotOrder order7 = new LotOrder("粤BHC9467","7");
		LotOrder order8 = new LotOrder("粤NH76347","8");
		order8.setStatus(STATUS_WAITUNPARK);
		order8.setType(TYPE_PARKOUTCAR);
		order8.setParkingBoy(user2);
		order8.setPlateNumber("粤C12345");
		order8.setParkingLotName("oocl停车场1");
	    order7.setStatus(STATUS_WAITUNPARK);
	    order7.setType(TYPE_PARKOUTCAR);
	    order7.setParkingBoy(user2);
	    order7.setPlateNumber("粤C32412");
	    order7.setParkingLotName("oocl停车场2");
		orderRepository.saveAll(Arrays.asList(order1,order2,order3,order4,order5,order6,order7,order8));
        System.out.println("===============未抢订单初始化完成=============");
    }
}
