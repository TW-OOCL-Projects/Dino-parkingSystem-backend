package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.LotOrder;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LotOrderRepositoryTest {

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
        LotOrder lotOrder = new LotOrder("park", "a1234", "parking", "qwerasdf1234");

        //when
        LotOrder lotOrder1 = orderRepository.save(lotOrder);

        //then
        assertThat(lotOrder1.getPlateNumber(), is("a1234"));
    }
}