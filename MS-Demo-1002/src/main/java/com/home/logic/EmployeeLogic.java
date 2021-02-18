package com.home.logic;

import java.util.List;
import java.util.Optional;

import com.home.bean.Employee;

public interface EmployeeLogic {

	Optional<Employee> getEmployeeById(int id);

	List<Employee> getEmployees();

	Employee saveEmployee(Employee emp);

}
