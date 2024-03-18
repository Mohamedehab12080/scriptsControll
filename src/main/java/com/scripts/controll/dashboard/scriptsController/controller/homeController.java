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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class homeController {

	@Autowired
	private adminRepository adminRepository;

	@GetMapping
	public String loginForm() {
		return "login";
	}

	@GetMapping("/loginExec")
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Optional<Admin> user = adminRepository.findByEmail(username);
		if (user.isPresent() && user.get().getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user.get());
			return "redirect:/user/getPage";
		} else {
			model.addAttribute("error", "Invalid 	username or password");
			return "redirect:/";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

//	 
//	 @PostMapping("/login")
//	 public String LoginFunction(@ModelAttribute("user") Admin admin)
//	 {
//		 Optional<Admin> adminReturn=adminRepository.findByEmail(admin.getEmail());
//	     if(adminReturn.isPresent())
//	     {
//	    	 if(adminReturn.get().getPassword().equals(admin.getPassword()))
//	    	 {
//	    		 return "redirect:/user/getPage";
//	    	 }else 
//	    	 {
//	    		 return "redirect:/?wrong_pass";
//	    	 }
//	     }else 
//	     {
//	    	 return "redirect:/?wrong_email";
//	     }
//		
//	    }
}
