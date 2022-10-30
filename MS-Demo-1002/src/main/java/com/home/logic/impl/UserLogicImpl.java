package com.home.logic.impl;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.home.bean.User;
import com.home.dao.UserDao;
import com.home.logic.UserLogic;

@Component
public class UserLogicImpl implements UserLogic {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserLogicImpl.class);

	@Autowired
	private UserDao employeeDao;

	@Override
	public Optional<User> getEmployeeById(int id) {
		Optional<User> employee = employeeDao.getEmployeeById(id);
		byte[] bytesPassword = Base64.getDecoder().decode(employee.get().getEmpPassword());
		try {
			String passwordStr = new String(bytesPassword, "UTF-8");
			employee.get().setEmpPassword(passwordStr);
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("UserLogicImpl UnsupportedEncodingException :::: " + e);
		}
		return employee;
	}

	@Override
	public List<User> getEmployees() {
		List<User> employees = employeeDao.getEmployees();
		for (User user : employees) {
			byte[] bytesPassword = Base64.getDecoder().decode(user.getEmpPassword());
			try {
				String passwordStr = new String(bytesPassword, "UTF-8");
				user.setEmpPassword(passwordStr);
			} catch (UnsupportedEncodingException e) {
				LOGGER.info("UserLogicImpl UnsupportedEncodingException :::: " + e);
			}

		}
		return employees;
	}

	@Override
	public User saveEmployee(User emp) {
		LOGGER.info("UserLogicImpl saveEmployee :::: START");
		if (emp.getEmpPassword() != null) {
			String encodePassword = Base64.getEncoder().encodeToString(emp.getEmpPassword().getBytes());
			emp.setEmpPassword(encodePassword);
		}
		return employeeDao.saveEmployee(emp);
	}

}
