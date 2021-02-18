package com.home.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.bean.Employee;
import com.home.dao.EmployeeDao;
import com.home.repository.EmployeeRepository;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private EmployeeRepository employeeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	@Override
	public List<Employee> getEmployees() {
		LOGGER.info("EmployeeDaoImpl getEmployees method   :: START");
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		LOGGER.info("EmployeeDaoImpl getEmployeeById method :: START");
		return employeeRepository.findById(id);
	}

	@Override
	public Employee saveEmployee(Employee emp) {

		return employeeRepository.save(emp);
	}

}
