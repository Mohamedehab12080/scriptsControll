package com.scripts.controll.dashboard.scriptsController.service;

import java.util.List;
import java.util.Optional;

import com.scripts.controll.dashboard.scriptsController.model.cokies;


public interface cokiesServiceInterface {
	Optional<cokies> findById(Long id);
	cokies findByOrderLimit1();
	String deleteById(Long id);
	cokies findByName(String cokiesName);
	String insert(cokies cokies);
	List<String> insertAll(List<cokies> cokiesList);
	List<cokies> findAll();
	void truncateTable();
}
