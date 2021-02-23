package com.home.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.home.bean.User;
import com.home.dao.UserDao;
import com.home.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository employeeRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public List<User> getEmployees() {
		LOGGER.info("EmployeeDaoImpl getEmployees method   :: START");
		return employeeRepository.findAll();
	}

	@Override
	public Optional<User> getEmployeeById(int id) {
		LOGGER.info("EmployeeDaoImpl getEmployeeById method :: START");
		return employeeRepository.findById(id);
	}

	@Override
	public User saveEmployee(User emp) {

		return employeeRepository.save(emp);
	}

}
