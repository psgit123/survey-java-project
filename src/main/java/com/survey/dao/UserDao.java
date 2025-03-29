package com.survey.dao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.survey.repository.UserRepository;

@Service
public class UserDao {
	
	@Autowired UserRepository userRepository;

	public String getUserPasswordByUsername(String username) {
		
		String pass="";
		
		pass = userRepository.getUserPasswordByUsername(username);
		
		return  pass;
	}

}
