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

import com.home.bean.Employee;


@RequestMapping(value = "/empApi")
public interface EmployeeController {

	@GetMapping(value = "/employee/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable("id") int id);

	@GetMapping(value = "/employees")
	public ResponseEntity<List<Employee>> getEmployees();
	
	@PostMapping(value = "/saveEmp")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee);
	
	@PutMapping("/employee/{id}")
	  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee);

}
