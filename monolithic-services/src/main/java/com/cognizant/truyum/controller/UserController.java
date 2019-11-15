package com.cognizant.truyum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.truyum.exception.UserAlreadyExistsException;
import com.cognizant.truyum.model.User;
import com.cognizant.truyum.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public void signUp(@RequestBody @Valid User user) throws UserAlreadyExistsException{
		userService.signUp(user);
	}
}
