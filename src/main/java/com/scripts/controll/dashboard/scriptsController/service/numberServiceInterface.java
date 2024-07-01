package com.scripts.controll.dashboard.scriptsController.service;

import java.util.List;
import java.util.Optional;

import com.scripts.controll.dashboard.scriptsController.model.Number;
public interface numberServiceInterface {

	Optional<Number> findById(Long id);
	Number findByOrderLimit1();
	String deleteById(Long id);
	Number findByName(String numberName);
	String insert(Number number);
	List<String> insertAll(List<Number> numbersList);
	List<Number> findAll();
	void truncateTable();
	
}
