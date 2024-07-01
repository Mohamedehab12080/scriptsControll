package com.scripts.controll.dashboard.scriptsController.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripts.controll.dashboard.scriptsController.model.Role;

@Repository
public interface roleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}
