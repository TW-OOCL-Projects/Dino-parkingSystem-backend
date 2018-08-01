package com.oocl.dino_parking_system.repositorys;

import com.oocl.dino_parking_system.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
