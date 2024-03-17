package com.scripts.controll.dashboard.scriptsController.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.repository.userRepository;

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
		if(exists==null)
		{
			userRepo.save(user);
			return "Added";
		}else 
		{
			return  user.getEmail()+" already exists";
		}
	}

	@Override
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
				return "enabled";
			}
		}
	}

	@Override
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
				return "enabled";
			}
		}
	}

	@Override
	public String deleteUserById(Long id) {
		User exists=findById(id);
		if(exists!=null)
		{
			return "";
		}else 
		{
			return "";
		}
	}

}
