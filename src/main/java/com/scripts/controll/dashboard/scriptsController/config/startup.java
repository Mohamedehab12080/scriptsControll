package com.scripts.controll.dashboard.scriptsController.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scripts.controll.dashboard.scriptsController.model.Admin;
import com.scripts.controll.dashboard.scriptsController.model.Role;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.repository.adminRepository;
import com.scripts.controll.dashboard.scriptsController.repository.roleRepository;
import com.scripts.controll.dashboard.scriptsController.service.userServiceInterface;

import jakarta.transaction.Transactional;
@Component
public class startup implements CommandLineRunner{

	
//	@Autowired
//	private userServiceInterface userServiceI;
//	@Autowired 
//	private adminRepository adminRepo;
//	
//	@Autowired 
//	private roleRepository roleRepo;
//	
	@Override
	@Transactional
	public void run(String... args) throws Exception {

	}
	

}
