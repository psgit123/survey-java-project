package com.survey.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.survey.bean.UserBean;
import com.survey.service.UserService;

import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {
	
	
	@Autowired UserService userService;
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(HttpServletRequest request, HttpServletResponse response, @RequestBody UserBean user) throws IOException {

	    Map<String, String> responseMap = new HashMap<>();

//	    String username = request.getParameter("username");
//	    String password = request.getParameter("password");
	    
	    String username = user.getUsername();
	    String password = user.getPassword();
	    
	    
	    String storedPassword =  userService.getUserPasswordByUsername(username); 
	    
	    password = getMd5(password) ;
	    

	    if (storedPassword != null && storedPassword.equals(password)) {

	        HttpSession session = request.getSession(); 
	        session.setAttribute("user", username);
	        
	        responseMap.put("code", "1000");
	        responseMap.put("message", "Login successful. Session ID: " + session.getId());
	        
	        
	    } 
	    else if(storedPassword == null){

	        responseMap.put("code", "1001");
	        responseMap.put("message", "Invalid email!");
	         
	    }
	    else {
	    	responseMap.put("code", "1001");
	        responseMap.put("message", "Invalid password!");
	        
	    }
	    
	    return responseMap;
	}
	
	
	

	
	public static String getMd5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private HashMap<String, Object> createResponse(String msgCode, String message) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("status_code", msgCode);
		map.put("message", message);
		return map;
	}
	
	
	
}
