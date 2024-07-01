package com.scripts.controll.dashboard.scriptsController.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.DTO.userDtoMapper;
import com.scripts.controll.dashboard.scriptsController.model.Admin;
import com.scripts.controll.dashboard.scriptsController.model.Number;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.model.cokies;
import com.scripts.controll.dashboard.scriptsController.service.cokiesServiceInterface;
import com.scripts.controll.dashboard.scriptsController.service.numberServiceInterface;
import com.scripts.controll.dashboard.scriptsController.service.runningScriptServiceInterface;
//Your imports here
import com.scripts.controll.dashboard.scriptsController.service.userServiceInterface;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private runningScriptServiceInterface runningSecriptServiceI;
	@Autowired
	private userServiceInterface userServiceI;
	
	@Autowired
	private numberServiceInterface numberServiceI;
	@Autowired
	private cokiesServiceInterface cokiesServiceI;
 @PostMapping("/runScript")
 public ResponseEntity<String> runScript(@RequestParam String scriptName) {
	 
	 return runningSecriptServiceI.runScriptProcess(scriptName);
 }
 
 @GetMapping("/getPage")
 public String getPage(HttpSession session, Model model)
 {
	Admin user=(Admin) session.getAttribute("user");
	if(user!=null)
	{
		 model.addAttribute("userDto",new userDto());
		 List<User> userList=userServiceI.findAllUsers();
		 List<userDto> userDtoList=userList.stream().map(this::convertUserToDto).collect(Collectors.toList());
		 model.addAttribute("userList",userDtoList);
		 String copyRights=(char) 169+" 2024 F.R Agency. All rights reserved.";
		 model.addAttribute("copy",copyRights);
		 model.addAttribute("userName",user.getName());
		 return "add-user";
	}else 
	{
		return "redirect:/";
	}
 }
 	
 @PostMapping("/addUser")
 public String insertUser(HttpSession session,@ModelAttribute("userDto") userDto userDto)
 {
	 Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
		 String resultofInsertion=userServiceI.insertUser(convertDtotoUser(userDto));
		 if(resultofInsertion.contains("Added"))
		 {
			 return "redirect:/user/getPage?success_add";
		 }else if(resultofInsertion.contains("exists"))
		 {
			 return "redirect:/user/getPage?user_exists";
		 }else 
		 {
			 return "redirect:/user/getPage?not_added";
		 }
		}else 
		{
			return "redirect:/";
		}
 }

 @GetMapping("/insertNumbers")
 public String insertNumbers(@ModelAttribute("numberRequest") numberRequest numberRequest)
 {
	 List <String> insertedNumbers =numberServiceI.insertAll(numberRequest.setAndGetNumberObject());
	 if(!insertedNumbers.isEmpty())
	 {
		 return "redirect:/user/getNumber?Inserted";
	 }else
	 {
		 return "redirect:/user/getNumber?something_wrong";
	 }
 }
 
 @GetMapping("/getNumber")
 public String getPageIndex(HttpSession session,Model model)
 {
		Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
			List<Number> numberList=numberServiceI.findAll();
			model.addAttribute("numberList",numberList);
			model.addAttribute("numberRequest",new numberRequest());
			return "index";
		}else 
		{
			return "redirect:/";
		}
 }
 
 @GetMapping("/truncateTable")
 public String deleteAll()
 {
	 numberServiceI.truncateTable();
	 return "redirect:/user/getNumber?Truncated";
 }
 
 
 @GetMapping("/insertCokies")
 public String insertCokies(@ModelAttribute("cokiesRequest") cokiesRequest cokiesRequest)
 {
	 List <String> insertedCokies =cokiesServiceI.insertAll(cokiesRequest.setAndGetCokiesObject());
	 if(!insertedCokies.isEmpty())
	 {
		 return "redirect:/user/getCokies?Inserted";
	 }else
	 {
		 return "redirect:/user/getCokies?something_wrong";
	 }
 }
 
 @GetMapping("/getCokies")
 public String getPageCokies(HttpSession session,Model model)
 {
		Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
			List<cokies> cokiesList=cokiesServiceI.findAll();
			model.addAttribute("cokiesList",cokiesList);
			model.addAttribute("cokiesRequest",new cokiesRequest());
			return "cokies";
		}else 
		{
			return "redirect:/";
		}
 }
 
 @GetMapping("/truncateCokiesTable")
 public String deleteAllCokies()
 {
	 cokiesServiceI.truncateTable();
	 return "redirect:/user/getCokies?Truncated";
 }
 
 @GetMapping("/deleteCokies/{id}")
 public String deleteCokies(HttpSession session,@PathVariable("id") Long id)
 {
	 Admin user=(Admin) session.getAttribute("user");
	 if(user!=null)
	 {
		 String result =cokiesServiceI.deleteById(id);
		 if(result.equals("deleted"))
		 {
			 return "redirect:/user/getCokies?Deleted";
		 }else 
		 {
			 return "redirect:/user/getCokies?can't be deleted";
		 }
	 }else 
	 {
		 return "redirect:/";
	 }
	 
 }
 
 @GetMapping("/deleteNumber/{id}")
 public String deleteNumber(HttpSession session,@PathVariable("id") Long id)
 {
	 Admin user=(Admin) session.getAttribute("user");
	 if(user!=null)
	 {
		 String result =numberServiceI.deleteById(id);
		 if(result.equals("deleted"))
		 {
			 return "redirect:/user/getNumber?Deleted";
		 }else 
		 {
			 return "redirect:/user/getNumber?can't be deleted";
		 }
	 }else 
	 {
		 return "redirect:/";
	 }
 }
 
 @GetMapping("/delete/{id}")
 public String deleteUser(HttpSession session,@PathVariable("id") Long id)
 {
	 Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
			 String resultOfdelete=userServiceI.deleteUserById(id);
			if(resultOfdelete.contains("Deleted"))
			{
				return"redirect:/user/getPage?deleted";
			}else 
			{
				return"redirect:/user/getPage?not_found";
			}
		}else
		{
			return "redirect:/";
		}
 }
 
 
 @GetMapping("/disable/{id}")
 public String disableUserById(HttpSession session,@PathVariable("id") Long id)
 {
	 Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
	 String result=userServiceI.disableUserById(id);
	 if(result.equals("disabled"))
	 {
		 return "redirect:/user/getPage?disabled_success";
	 }else if(result.contains("already"))
	 {
		 return "redirect:/user/getPage?already_disabled";
	 }else 
	 {
		 return "redirect:/user/getPage?not_found";
	 }
		}else 
		{
			return "redirect:/";
		}
 }
 

 @GetMapping("/enable/{id}")
 public String enableUserById(HttpSession session,@PathVariable("id") Long id)
 {
	 Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
	 String result=userServiceI.enableUserById(id);
	 if(result.equals("enabled"))
	 {
		 return "redirect:/user/getPage?enabled_success";
	 }else if(result.contains("already"))
	 {
		 return "redirect:/user/getPage?already_enabled";
	 }else 
	 {
		 return "redirect:/user/getPage?not_found";
	 }
		}
		else 
		{
			return "redirect:/";	
		}
 }
 
 
 @PostMapping("/updateUsers") 
 public String update(HttpSession session,@ModelAttribute("userDto") userDto userDto)
 {

	 Admin user=(Admin) session.getAttribute("user");
		if(user!=null)
		{
	 	String result=userServiceI.updateUser(userDto);
	 	if(result.equals("updated"))
	 	{
			 return "redirect:/user/getPage?updated_success";
	
	 	}else if(result.contains("can"))
	 	{
			 return "redirect:/user/getPage?error";

	 	}else 
	 	{
			 return "redirect:/user/getPage?not_found";

	 	}
		}else 
		{
			return "redirect:/";	
		}
 }
 
 @GetMapping("/findByEmail")
 public ResponseEntity<userDto> findByEmail(HttpSession session,@RequestParam String email)
 {
	 if(userServiceI.findByEmail(email)!=null)
	 {
		 return ResponseEntity.ok(convertUserToDto(userServiceI.findByEmail(email)));
	 }else 
	 {
		 return ResponseEntity.notFound().build();
	 }
		
 }
 
 @GetMapping("/findByName")
 public ResponseEntity<userDto> findByName(HttpSession session,@RequestParam String name)
 {
	 if(userServiceI.findByName(name)!=null)
	 {
		 return ResponseEntity.ok(convertUserToDto(userServiceI.findByName(name)));
	 }else 
	 {
		 return ResponseEntity.notFound().build();
	 }
 }
// 
 @GetMapping("/findAll")
 public List<userDto> findAll(HttpSession session)
 {
	 List<User> userList=userServiceI.findAllUsers();
	 return userList.stream().map(this::convertUserToDto).collect(Collectors.toList());
 }
 
 
 private User convertDtotoUser(userDto user)
 {
	 return userDtoMapper.mapDtoToUser(user);
 }
 private userDto convertUserToDto(User user)
 {
	 return userDtoMapper.mapUserToDTO(user);
 }
 
}
