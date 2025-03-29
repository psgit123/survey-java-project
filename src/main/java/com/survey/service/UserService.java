package com.survey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.dao.UserDao;

@Service
public class UserService {
	
	@Autowired UserDao userDao;
	
	
	public String getUserPasswordByUsername(String username) {
		
		
		return userDao.getUserPasswordByUsername(username);
	}

}
