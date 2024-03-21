package com.scripts.controll.dashboard.scriptsController.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.repository.userRepository;

import jakarta.transaction.Transactional;

@Service
public class userServiceImpl implements userServiceInterface{

	@Autowired
	private userRepository userRepo;
	
	@Override
	public User findByName(String name) {
		Optional<User> fetched=userRepo.findByName(name);
		if(fetched.isPresent())
		{
			return fetched.get();
		}else 
		{
			return null;
		}
	}

	@Override
	public User findById(Long id) {
		Optional<User> fetched=userRepo.findById(id);
		if(fetched.isPresent())
		{
			return fetched.get();
		}else 
		{
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		Optional<User> fetched=userRepo.findByEmail(email);
		if(fetched.isPresent())
		{
			return fetched.get();
		}else 
		{
			return null;
		}
	}

	@Override
	public String insertUser(User user) {
		User exists=findByEmail(user.getEmail());
		User inserted;
		if(exists==null)
		{
			inserted=userRepo.save(user);
			if(inserted!=null)
			{
				return "Added";
			}else 
			{
				return "can't be added";
			}
			
		}else 
		{
			return  user.getEmail()+" already exists";
		}
		
	}

	@Override
	@Transactional
	public String enableUserById(Long id) {
		User exists=findById(id);
		if(exists==null)
		{
			return "User not found";
		}else 
		{
			if(exists.isEnabled())
			{
				return "already enabled";
			}else 
			{
				exists.setEnabled(true);
				userRepo.save(exists);
				return "enabled";
			}
		}
	}

	@Override
	@Transactional
	public String enableUserByEmail(String email) {
		User exists=findByEmail(email);
		if(exists==null)
		{
			return "User not found";
		}else 
		{
			if(exists.isEnabled())
			{
				return "already enabled";
			}else 
			{
				exists.setEnabled(true);
				userRepo.save(exists);
				return "enabled";
			}
		}
	}

	@Override
	@Transactional
	public String deleteUserById(Long id) {
		User exists=findById(id);
		if(exists!=null)
		{
			userRepo.deleteById(id);
			return "Deleted";
			
		}else 
		{
			return "User not found";
		}
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String disableUserById(Long id) {
		User exists=findById(id);
		if(exists==null)
		{
			return "User not found";
		}else 
		{
			if(exists.isEnabled())
			{
				exists.setEnabled(false);
				userRepo.save(exists);
				return "disabled";
			}else 
			{
				return "already disabled";
			}
		}
	}

	@Override
	@Transactional
	public String updateUser(userDto user) {
		User oldUser=findById(user.getId());
		if(oldUser!=null)
		{
			if(user.getName()!=null)
			{
				oldUser.setName(user.getName());
			}
			
			if(user.getEmail()!=null)
			{
				oldUser.setEmail(user.getEmail());
			}
			
			if(user.getPassword()!=null)
			{
				oldUser.setPassword(user.getPassword());
			}
			
			if(user.getMobileNumber()!=null)
			{
				oldUser.setMobileNumber(user.getMobileNumber().strip());
			}
			
			if(user.getNote()!=null)
			{
				oldUser.setNote(user.getNote());
			}
			
			oldUser.setEnabled(user.isEnabled());
			
			User userUpdated =userRepo.save(oldUser);
			if(userUpdated!=null)
			{
				return "updated";
			}else 
			{
				return "can't be updated";
			}
		}else 
		{
			return "User not found";
		}
	}

}
