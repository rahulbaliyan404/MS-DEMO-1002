package com.home.controller.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.home.bean.User;
import com.home.controller.UserController;
import com.home.logic.UserLogic;
import com.home.repository.UserRepository;

@RestController
public class UserControllerImpl implements UserController {

	@Autowired
	private UserLogic employeeLogic;

	@Autowired
	private UserRepository employeeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerImpl.class);

	@Override
	public Optional<User> getEmployeeById(int id) {
		LOGGER.info("EmployeeControllerImpl getEmployeeById method :: START");
		return employeeLogic.getEmployeeById(id);
	}

	@Override
	public ResponseEntity<List<User>> getEmployees() {
		LOGGER.info("EmployeeControllerImpl getEmployees method    :: START");
		try {
			List<User> employees = employeeLogic.getEmployees();

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info("EmployeeControllerImpl getEmployees method :: Exception  " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<User> saveEmployee(User employee) {
		LOGGER.info("EmployeeControllerImpl saveEmployee method    :: START");
		try {
			User emp = employeeLogic.saveEmployee(employee);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<User> updateEmployee(int id, User employee) {
		LOGGER.info("EmployeeControllerImpl updateEmployee method    :: START");
		try {
			Optional<User> employeeData = employeeRepository.findById(id);

			if (employeeData.isPresent()) {
				User emp = employeeData.get();
				emp.setEmpName(employee.getEmpName());
				emp.setEmpPassword(employee.getEmpPassword());
				emp.setEmpEmail(employee.getEmpEmail());
				emp.setEmpAge(employee.getEmpAge());
				emp.setEmpSalary(employee.getEmpSalary());
				return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.OK);
			}

			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			LOGGER.info("EmployeeControllerImpl updateEmployee method    :: EXCEPTION " + e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
}
