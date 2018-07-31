package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.Employee;
import com.oocl.dino_parking_system.entities.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void demotest() {
        Employee employee = new Employee("a", "111@as.com", "1324564", "aas", "adf");
        Order order = new Order("asd", employee, "asdf", "adf", "1234");
        orderRepository.save(order);
        Order o = orderRepository.findById(1L).get();
        employee.setOrders(Arrays.asList(o));

        employeeRepository.save(employee);
        List<Employee> employees = employeeRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        assertThat(orders.get(0).getType(), is("asd"));
        assertThat(employees.get(0).getOrders().get(0).getType(), is("asd"));

    }
}