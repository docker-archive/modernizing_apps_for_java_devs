package com.docker.register.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.docker.register.domain.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private CrudRepository<User, String> repository;
	
	@Autowired
	public UserController(CrudRepository<User, String> repository) {
		this.repository = repository;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public User add(@RequestBody @Valid User user) {
		return repository.save(user);
	}
	
	@RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
	public void deleteById(@PathVariable String id) {
		repository.delete(id);
	}
}
