package com.tanaka.desafiopicpay.desafiopicpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanaka.desafiopicpay.desafiopicpay.domain.user.User;
import com.tanaka.desafiopicpay.desafiopicpay.dtos.UserDTO;
import com.tanaka.desafiopicpay.desafiopicpay.services.UserService;

@RestController()
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDTO user){
		User newUser = userService.createUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = this.userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
