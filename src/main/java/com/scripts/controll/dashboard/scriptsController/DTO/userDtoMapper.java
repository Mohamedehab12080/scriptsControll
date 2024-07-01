package com.scripts.controll.dashboard.scriptsController.DTO;

import com.scripts.controll.dashboard.scriptsController.model.User;

public class userDtoMapper {

	public static userDto mapUserToDTO(User user)
	{
		userDto dto=new userDto();
		dto.setId(user.getId());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setLastModifiedBy(user.getLastModifiedBy());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setEnabled(user.isEnabled());
		dto.setNote(user.getNote());
		dto.setPassword(user.getPassword());
		dto.setMobileNumber(user.getMobileNumber());
		
		return dto;
	}
	
	public static User mapDtoToUser(userDto user)
	{
		User dto=new User();
		dto.setId(user.getId());
		dto.setCreatedBy(user.getCreatedBy());
		dto.setCreatedDate(user.getCreatedDate());
		dto.setLastModifiedBy(user.getLastModifiedBy());
		dto.setLastModifiedDate(user.getLastModifiedDate());
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setEnabled(user.isEnabled());
		dto.setNote(user.getNote());
		dto.setPassword(user.getPassword());
		dto.setMobileNumber(user.getMobileNumber());
		
		return dto;
	}
}
