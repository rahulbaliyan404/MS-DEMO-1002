package com.home.logic;

import java.util.List;
import java.util.Optional;

import com.home.bean.User;

public interface UserLogic {

	Optional<User> getEmployeeById(int id);

	List<User> getEmployees();

	User saveEmployee(User emp);

}
