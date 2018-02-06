package com.docker.UserSignup.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.text.SimpleDateFormat;


import com.docker.UserSignup.model.User;
import com.docker.UserSignup.model.UserLogin;
import com.docker.UserSignup.service.UserService;

@Controller
@SessionAttributes("user")
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();		
		model.addAttribute("user", user);		
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {		
		if(result.hasErrors()) {
			return "signup";
		} else if(userService.findByUserName(user.getUserName())) {
			model.addAttribute("message", "User Name exists. Try another user name");
			return "signup";
		} else {
			String uri = new String("http://messageservice:8090/user");
			RestTemplate rt = new RestTemplate();
			Long id = new Long(new Random().nextInt(10000)+10000);
			user.setId(id);
			System.out.println(user.getDateOfBirth());
			try {
				rt.postForObject(uri, user, String.class);
			} catch(Exception e) {
				System.out.println(e);
			}
			model.addAttribute("message", "Saved user details");	

			return "redirect:login.html";
		}
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {			
		UserLogin userLogin = new UserLogin();		
		model.addAttribute("userLogin", userLogin);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result) {
		if (result.hasErrors()) {
			return "login";
		} else {
			boolean found = userService.findByLogin(userLogin.getUserName(), userLogin.getPassword());
			if (found) {				
				return "success";
			} else {				
				return "failure";
			}
		}
		
	}
}
