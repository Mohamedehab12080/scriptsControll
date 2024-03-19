package com.scripts.controll.dashboard.scriptsController.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.DTO.userDtoMapper;
import com.scripts.controll.dashboard.scriptsController.model.Admin;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.service.runningScriptServiceInterface;
//Your imports here
import com.scripts.controll.dashboard.scriptsController.service.userServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/controller")
public class restController {

	@Autowired
	private runningScriptServiceInterface runningSecriptServiceI;
	@Autowired
	private userServiceInterface userServiceI;
	
 @PostMapping("/runScript")
 public ResponseEntity<String> runScript(@RequestParam String scriptName) {
	 
	 return runningSecriptServiceI.runScriptProcess(scriptName);
 }
 
 @PostMapping("/addUser")
 public ResponseEntity<String> insertUser(@RequestBody userDto user)
 {
	 return ResponseEntity.ok(userServiceI.insertUser(convertDtotoUser(user)));
 }

 
 @PutMapping("/updateUser")
 public ResponseEntity<String> updateUser(@RequestBody updateRequest request)
 {
	 String result=userServiceI.updateUser(request.getUser());
	 return ResponseEntity.ok(result);
 }
 
 @DeleteMapping("/delete")
 public ResponseEntity<String> deleteUser(@RequestParam Long userId)
 {
	 return ResponseEntity.ok(userServiceI.deleteUserById(userId));
 }
 
 @PutMapping("/enable")
 public ResponseEntity<String> enableUserByEmail(@RequestParam String email)
 {
	 return ResponseEntity.ok(userServiceI.enableUserByEmail(email));
 }
 
 @PutMapping("/enable2")
 public ResponseEntity<String> enableUserById(@RequestParam Long userId)
 {
	 return ResponseEntity.ok(userServiceI.enableUserById(userId));
 }
 
 @GetMapping("/findByEmail")
 public ResponseEntity<?> findByEmail(@RequestParam String email)
 {
	 User fetched=userServiceI.findByEmail(email);
	 if(fetched!=null)
	 {
		  LocalDateTime createdDate = fetched.getCreatedDate();
	        LocalDateTime now = LocalDateTime.now();
	        
	        Duration duration = Duration.between(createdDate, now);

	        // Get the difference in days
	        long daysDifference = duration.toDays();

	        // Add the difference to the current date
	        LocalDateTime durationDate = now.plus((30-daysDifference)+1, ChronoUnit.DAYS);
	        // Check if the duration is less than one day
	        if (duration.toDays() < 1) {
	            duration = Duration.ofDays(1); // Consider it as one day
	        }
	        // Format the date
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = durationDate.format(formatter);
	        responseOfCheck response =new responseOfCheck();
	        response.setCreatedDate(createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        response.setDurationDate(formattedDate);
	        response.setStatus("success");
		 return ResponseEntity.ok(response);
	 }else 
	 {
		 return ResponseEntity.notFound().build();
	 }
 }
 
 @PostMapping("/check")
 public ResponseEntity<?> findByEmailAndBassword(@RequestBody checkRequest checkRequest)
 {
	User fetched=userServiceI.findByEmail(checkRequest.getUseremail().strip());
	 if(fetched!=null&&fetched.getPassword().equals(checkRequest.getUserpassword()) 
			 &&checkRequest.getMacAddress().equalsIgnoreCase(fetched.getMobileNumber()))
	 {
		 	
	        LocalDateTime createdDate = fetched.getCreatedDate();
	        LocalDateTime now = LocalDateTime.now();
	        
	        Duration duration = Duration.between(createdDate, now);

	        // Get the difference in days
	        long daysDifference = duration.toDays();

	        // Add the difference to the current date
	        LocalDateTime durationDate = now.plus((30-daysDifference)+1, ChronoUnit.DAYS);
	        // Check if the duration is less than one day
	        if (duration.toDays() < 1) {
	            duration = Duration.ofDays(1); // Consider it as one day
	        }
	        // Format the date
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDate = durationDate.format(formatter);
	        responseOfCheck response =new responseOfCheck();
	        response.setCreatedDate(createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	        response.setDurationDate(formattedDate);
	        response.setStatus("success");
	        response.setAddress(fetched.getMobileNumber());
		 return ResponseEntity.ok(response);
	 }else 
	 {
		 return ResponseEntity.notFound().build();
	 }
 }
 
 @GetMapping("/findByName")
 public ResponseEntity<userDto> findByName(HttpSession session, @RequestParam String name)
 {
	Admin user=(Admin)session.getAttribute("user");
	if(user!=null)
	{
		if(userServiceI.findByName(name)!=null)
		 {
			 return ResponseEntity.ok(convertUserToDto(userServiceI.findByName(name)));
		 }else 
		 {
			 return ResponseEntity.notFound().build();
		 }
	}else 
	{
		return ResponseEntity.notFound().build();
	}
 }
 
 @GetMapping("/findAll")
 public List<userDto> findAll(HttpSession session)
 {
	 Admin user=(Admin)session.getAttribute("user");
	if(user!=null)
	{
		 List<User> userList=userServiceI.findAllUsers();
		 return userList.stream().map(this::convertUserToDto).collect(Collectors.toList());
	}else 
	{
		return null;
	}
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
