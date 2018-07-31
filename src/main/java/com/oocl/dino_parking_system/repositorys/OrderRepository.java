package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
