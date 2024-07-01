package com.scripts.controll.dashboard.scriptsController.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.scripts.controll.dashboard.scriptsController.model.runningScript;

public interface runningScriptServiceInterface {

	List<runningScript> findAll();
	runningScript findById(Long id);
	runningScript findByScriptName(String scriptName);
	String updateScript(Long id ,runningScript entity);
	String deleteById(Long id);
	ResponseEntity<String> runScriptProcess(String scriptName);
	String stopScriptProcess(String scriptName);
}
