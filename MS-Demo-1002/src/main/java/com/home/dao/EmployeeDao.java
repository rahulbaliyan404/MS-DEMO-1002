package com.home.dao;
import java.util.List;
import java.util.Optional;

import com.home.bean.Employee;

public interface EmployeeDao {

	List<Employee> getEmployees();

	Optional<Employee> getEmployeeById(int id);

	Employee saveEmployee(Employee emp);

}
