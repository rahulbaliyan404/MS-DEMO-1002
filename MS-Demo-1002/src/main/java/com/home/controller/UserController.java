package com.home.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.bean.User;


@RequestMapping(value = "/demo1002")
public interface UserController {

	@GetMapping(value = "/user/{id}")
	public Optional<User> getEmployeeById(@PathVariable("id") int id);

	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getEmployees();
	
	@PostMapping(value = "/save")
	public ResponseEntity<User> saveEmployee(@RequestBody User employee);
	
	@PutMapping("/user/{id}")
	  public ResponseEntity<User> updateEmployee(@PathVariable("id") int id, @RequestBody User employee);

}
