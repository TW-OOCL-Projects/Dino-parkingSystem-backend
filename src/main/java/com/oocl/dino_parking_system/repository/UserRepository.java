package com.oocl.dino_parking_system.repository;

import com.oocl.dino_parking_system.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByUsername(String username);

	User findByUsernameAndPassword(String name, String password);
}
