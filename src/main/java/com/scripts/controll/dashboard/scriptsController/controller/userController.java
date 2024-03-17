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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.DTO.userDtoMapper;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.service.runningScriptServiceInterface;
//Your imports here
import com.scripts.controll.dashboard.scriptsController.service.userServiceInterface;


@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private runningScriptServiceInterface runningSecriptServiceI;
	@Autowired
	private userServiceInterface userServiceI;
	
 @PostMapping("/runScript")
 public ResponseEntity<String> runScript(@RequestParam String scriptName) {
	 
	 return runningSecriptServiceI.runScriptProcess(scriptName);
 }
 
 @GetMapping("/getPage")
 public String getPage(Model model)
 {
	 model.addAttribute("userDto",new userDto());
	 List<User> userList=userServiceI.findAllUsers();
	 List<userDto> userDtoList=userList.stream().map(this::convertUserToDto).collect(Collectors.toList());
	 model.addAttribute("userList",userDtoList);
	 String copyRights=(char) 169+" 2024 F.R Agency. All rights reserved.";
	 model.addAttribute("copy",copyRights);
	 return "add-user";
 }
 
 @PostMapping("/addUser")
 public String insertUser(@ModelAttribute("userDto") userDto userDto)
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
 }

 
 @GetMapping("/delete/{id}")
 public String deleteUser(@PathVariable("id") Long id)
 {
	 String resultOfdelete=userServiceI.deleteUserById(id);
	if(resultOfdelete.contains("Deleted"))
	{
		return"redirect:/user/getPage?deleted";
	}else 
	{
		return"redirect:/user/getPage?not_found";
	}
 }
 
 
 @GetMapping("/disable/{id}")
 public String disableUserById(@PathVariable("id") Long id)
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
 }
 

 @GetMapping("/enable/{id}")
 public String enableUserById(@PathVariable("id") Long id)
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
 
 
 @PostMapping("/updateUsers") 
 public String update(@ModelAttribute("userDto") userDto userDto)
 {
	 	String result=userServiceI.updateUser(userDto);
	 	if(result.equals("update"))
	 	{
			 return "redirect:/user/getPage?updated_success";
	
	 	}else if(result.contains("can"))
	 	{
			 return "redirect:/user/getPage?error";

	 	}else 
	 	{
			 return "redirect:/user/getPage?not_found";

	 	}
 }
 
 @GetMapping("/findByEmail")
 public ResponseEntity<userDto> findByEmail(@RequestParam String email)
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
 public ResponseEntity<userDto> findByName(@RequestParam String name)
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
 public List<userDto> findAll()
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
