package com.home.dao;
import java.util.List;
import java.util.Optional;

import com.home.bean.User;

public interface UserDao {

	List<User> getEmployees();

	Optional<User> getEmployeeById(int id);

	User saveEmployee(User emp);

}
