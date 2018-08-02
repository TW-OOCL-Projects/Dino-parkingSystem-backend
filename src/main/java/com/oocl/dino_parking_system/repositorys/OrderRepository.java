package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.LotOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<LotOrder, Long> {
    public List<LotOrder> findByStatus(String status);
}
