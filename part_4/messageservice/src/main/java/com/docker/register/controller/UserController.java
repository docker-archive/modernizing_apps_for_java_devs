package com.docker.register.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.docker.register.domain.User;
import com.docker.register.repository.UserRepository;;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserRepository repository;
	
	@Autowired
	public UserController(UserRepository repository) {
		this.repository = repository;
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public User add(@RequestBody @Valid User user) {
		return repository.save(user);
	}
}
