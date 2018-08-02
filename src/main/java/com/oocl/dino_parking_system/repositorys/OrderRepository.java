package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.LotOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<LotOrder, Long> {
}
