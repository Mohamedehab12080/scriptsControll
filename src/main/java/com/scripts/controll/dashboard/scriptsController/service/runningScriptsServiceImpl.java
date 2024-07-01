package com.scripts.controll.dashboard.scriptsController.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.scripts.controll.dashboard.scriptsController.model.runningScript;

@Service
public class runningScriptsServiceImpl implements runningScriptServiceInterface{

	@Override
	public List<runningScript> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public runningScript findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public runningScript findByScriptName(String scriptName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateScript(Long id, runningScript entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> runScriptProcess(String scriptName) {
		try {
	         String scriptPath = "scripts/" + scriptName;
	         ClassPathResource resource = new ClassPathResource(scriptPath);
	         if (!resource.exists()) {
	             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Script not found.");
	         }
	         String pythonExecutable = "C:\\Python311\\python.exe"; // Replace with the actual path
	         ProcessBuilder processBuilder = new ProcessBuilder(pythonExecutable, resource.getFile().getAbsolutePath());
	         processBuilder.redirectErrorStream(true); // Redirect error stream to output stream
	         Process process = processBuilder.start();

	         // Read and print script output
	         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	         StringBuilder output = new StringBuilder();
	         String line;
	         while ((line = reader.readLine()) != null) {
	             output.append(line).append("\n");
	         }

	         // Wait for the process to exit
	         int exitCode = process.waitFor();

	         if (exitCode == 0) {
	             return ResponseEntity.ok("Script executed successfully.\n" + output.toString());
	         } else {
	             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error running script.\n" + output.toString());
	         }
	     } catch (IOException | InterruptedException e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error running script: " + e.getMessage());
	     }

	}

	@Override
	public String stopScriptProcess(String scriptName) {
		// TODO Auto-generated method stub
		return null;
	}

}
