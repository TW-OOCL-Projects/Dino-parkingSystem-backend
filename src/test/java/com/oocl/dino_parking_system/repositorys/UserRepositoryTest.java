package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.LotOrder;
import com.oocl.dino_parking_system.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void demotest() {
        User user = new User("a", "111@as.com", "1324564", "aas", "adf");
        LotOrder lotOrder = new LotOrder("asd", "asdf", "adf", "1234");
        lotOrder.setUser(user);
        orderRepository.save(lotOrder);
        LotOrder o = orderRepository.findById(1L).get();
        user.setLotOrders(Arrays.asList(o));

        employeeRepository.save(user);
        List<User> users = employeeRepository.findAll();
        List<LotOrder> lotOrders = orderRepository.findAll();

        assertThat(lotOrders.get(0).getType(), is("asd"));
        assertThat(users.get(0).getLotOrders().get(0).getType(), is("asd"));

    }
}