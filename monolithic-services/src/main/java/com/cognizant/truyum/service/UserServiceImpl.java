package com.cognizant.truyum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.truyum.dao.UserDao;
import com.cognizant.truyum.exception.UserAlreadyExistsException;
import com.cognizant.truyum.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void signUp(User user) throws UserAlreadyExistsException {
		userDao.signUp(user);
	}

}
