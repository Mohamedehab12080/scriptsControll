package com.scripts.controll.dashboard.scriptsController.service;

import java.util.List;
import java.util.Optional;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.model.User;

public interface userServiceInterface {

	
	User findByName(String name);
	User findById(Long id);
	User findByEmail(String email);
	String insertUser(User user);
	String enableUserById(Long id);
	String enableUserByEmail(String email);
	String deleteUserById(Long id);
	List<User> findAllUsers();
	String disableUserById(Long id);
	String updateUser(userDto user);
	
}
