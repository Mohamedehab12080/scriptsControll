package com.scripts.controll.dashboard.scriptsController.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.scripts.controll.dashboard.scriptsController.model.Number;

import jakarta.transaction.Transactional;

@Repository
public interface numberRepository extends JpaRepository<Number, Long>{
	@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE number", nativeQuery = true)
    void truncateTable();
	Number findByNumber(String numberName);
	Optional<Number> findFirstByOrderByIdDesc();
}
