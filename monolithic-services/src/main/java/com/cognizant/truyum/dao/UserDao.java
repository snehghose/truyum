package com.cognizant.truyum.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

import com.cognizant.truyum.TruyumConstants;
import com.cognizant.truyum.exception.UserAlreadyExistsException;
import com.cognizant.truyum.model.User;

@Component
public class UserDao {
	
	@Autowired
	InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	public PasswordEncoder passwordEncoder() {
		TruyumConstants.LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}
	
	public void signUp(User user) throws UserAlreadyExistsException{
		if(!inMemoryUserDetailsManager.userExists(user.getUserName())) {
			inMemoryUserDetailsManager.createUser(org.springframework.security.core.userdetails.User
					.withUsername(user.getUserName()).password(passwordEncoder().encode(user.getPassword()))
					.roles("USER").build());
			TruyumConstants.LOGGER.debug("New User Created");
		}else {
			throw new UserAlreadyExistsException();
		}
	}
}
