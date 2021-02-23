package com.home.logic.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.bean.User;
import com.home.dao.UserDao;
import com.home.logic.UserLogic;

@Component
public class UserLogicImpl implements UserLogic{
	
	@Autowired
	private UserDao employeeDao;

	@Override
	public Optional<User> getEmployeeById(int id) {
		Optional<User> employee=employeeDao.getEmployeeById(id);
		return employee;
	}

	@Override
	public List<User> getEmployees() {
		
		return employeeDao.getEmployees();
	}

	@Override
	public User saveEmployee(User emp) {
		
		return employeeDao.saveEmployee(emp);
	}

}
