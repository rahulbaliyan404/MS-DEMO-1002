package com.home.controller.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.home.bean.Employee;
import com.home.controller.EmployeeController;
import com.home.logic.EmployeeLogic;
import com.home.repository.EmployeeRepository;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeLogic employeeLogic;

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeControllerImpl.class);

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		LOGGER.info("EmployeeControllerImpl getEmployeeById method :: START");
		return employeeLogic.getEmployeeById(id);
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployees() {
		LOGGER.info("EmployeeControllerImpl getEmployees method    :: START");
		try {
			List<Employee> employees = employeeLogic.getEmployees();

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
	public ResponseEntity<Employee> saveEmployee(Employee employee) {
		LOGGER.info("EmployeeControllerImpl saveEmployee method    :: START");
		try {
			Employee emp = employeeLogic.saveEmployee(employee);
			return new ResponseEntity<>(emp, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<Employee> updateEmployee(int id, Employee employee) {
		LOGGER.info("EmployeeControllerImpl updateEmployee method    :: START");
		try {
			Optional<Employee> employeeData = employeeRepository.findById(id);

			if (employeeData.isPresent()) {
				Employee emp = employeeData.get();
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
