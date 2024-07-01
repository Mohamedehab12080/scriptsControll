package com.scripts.controll.dashboard.scriptsController.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scripts.controll.dashboard.scriptsController.model.Number;
import com.scripts.controll.dashboard.scriptsController.model.cokies;

import jakarta.transaction.Transactional;

@Repository
public interface cokiesRepository extends JpaRepository<cokies, Long>{

	@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE cokies", nativeQuery = true)
    void truncateTable();
	
//	@Modifying
//    @Transactional
//    @Query(value = "DROP TABLE cokies", nativeQuery = true)
//    void dropTable();
	
	cokies findByCokies(String cokies);
	Optional<cokies> findFirstByOrderByIdDesc();

}
