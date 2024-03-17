package com.scripts.controll.dashboard.scriptsController.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scripts.controll.dashboard.scriptsController.model.Admin;
import com.scripts.controll.dashboard.scriptsController.repository.adminRepository;

@Controller
@RequestMapping("/")
public class homeController {

	@Autowired
	private adminRepository adminRepository;
	 
	 @GetMapping
	 public String Login(Model model)
	 {
		 Admin admin=new Admin();
	     model.addAttribute("user",admin);
		 return "login";
	 }
	 
	 @PostMapping("/login")
	 public String LoginFunction(@ModelAttribute("user") Admin admin)
	 {
		 Optional<Admin> adminReturn=adminRepository.findByEmail(admin.getEmail());
	     if(adminReturn.isPresent())
	     {
	    	 if(adminReturn.get().getPassword().equals(admin.getPassword()))
	    	 {
	    		 return "redirect:/user/getPage";
	    	 }else 
	    	 {
	    		 return "redirect:/?wrong_pass";
	    	 }
	     }else 
	     {
	    	 return "redirect:/?wrong_email";
	     }
		
	    }
}
