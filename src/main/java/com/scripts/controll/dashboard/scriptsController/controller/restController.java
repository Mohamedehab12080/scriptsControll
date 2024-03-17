package com.scripts.controll.dashboard.scriptsController.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scripts.controll.dashboard.scriptsController.DTO.userDto;
import com.scripts.controll.dashboard.scriptsController.DTO.userDtoMapper;
import com.scripts.controll.dashboard.scriptsController.model.User;
import com.scripts.controll.dashboard.scriptsController.service.runningScriptServiceInterface;
//Your imports here
import com.scripts.controll.dashboard.scriptsController.service.userServiceInterface;

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
 
 
 private User convertDtotoUser(userDto user)
 {
	 return userDtoMapper.mapDtoToUser(user);
 }
 private userDto convertUserToDto(User user)
 {
	 return userDtoMapper.mapUserToDTO(user);
 }
 
}
