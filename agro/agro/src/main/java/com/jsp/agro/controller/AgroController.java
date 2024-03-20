package com.jsp.agro.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jsp.agro.entity.User;
import com.jsp.agro.service.AgroService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class AgroController {
	
	@Autowired
	private AgroService service;
	
	// User Registration
	@PostMapping("/userRegisterd")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody  User user) {
		return service.register(user);
	}
	
	//user fetching fetch by id
	@GetMapping("/fetchUser")
	public ResponseEntity<ResponseStructure<User>> fetchById(@RequestParam int id){	
		return service.fetchById(id);
	}
	// fetch By all
	@GetMapping("/fetchAll")
	public ResponseEntity<ResponseStructure<List<User>>> fetchByAll(){	
		return service.fetchByAll();
	}
	
	//userLogin
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestParam String email,@RequestParam String pwd){
		  return service.login(email,pwd);
	}
	//userUpdating
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<User>>updateById(@RequestBody User user){
		return service.updateById(user);
	}
	
	//forgetPassword
	@GetMapping("/otp")
	public  ResponseEntity<ResponseStructure<Integer>>  forgetPassword(@RequestParam String email){
		return service.generateOtp(email);	
	}
	
	//delete
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<User>> deleteUserById(@RequestParam int id) {
		return service.deleteUserById(id);
	}
	

}
