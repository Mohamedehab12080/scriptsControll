package com.scripts.controll.dashboard.scriptsController.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripts.controll.dashboard.scriptsController.model.User;

@Repository
public interface userRepository extends JpaRepository<User,Long>{

	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
}
