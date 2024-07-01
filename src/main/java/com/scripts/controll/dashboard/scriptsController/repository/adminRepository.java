package com.scripts.controll.dashboard.scriptsController.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripts.controll.dashboard.scriptsController.model.Admin;

@Repository
public interface adminRepository  extends JpaRepository<Admin, Long>{

	Optional<Admin> findByEmail(String email);
}
