package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.Employee;
import com.oocl.dino_parking_system.entities.Order;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @After
    public void tearDown() {
        testEntityManager.clear();
    }

    @Test
    public void save() {
        //given
        Order order = new Order("park", "a1234", "parking", "qwerasdf1234");

        //when
        Order order1 = orderRepository.save(order);

        //then
        assertThat(order1.getPlateNumber(), is("a1234"));
    }
}