package com.home.logic.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.bean.Employee;
import com.home.dao.EmployeeDao;
import com.home.logic.EmployeeLogic;

@Component
public class EmployeeLogicImpl implements EmployeeLogic{
	
	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Optional<Employee> getEmployeeById(int id) {
		Optional<Employee> employee=employeeDao.getEmployeeById(id);
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		
		return employeeDao.getEmployees();
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		
		return employeeDao.saveEmployee(emp);
	}

}
