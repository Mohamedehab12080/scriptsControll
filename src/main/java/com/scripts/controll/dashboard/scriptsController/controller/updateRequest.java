package com.scripts.controll.dashboard.scriptsController.controller;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.model.User;

public class updateRequest {

	private userDto user;
	private Long userId;
	public userDto getUser() {
		return user;
	}
	public void setUser(userDto user) {
		this.user = user;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
