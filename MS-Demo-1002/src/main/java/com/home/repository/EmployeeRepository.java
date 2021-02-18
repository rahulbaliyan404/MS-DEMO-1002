package com.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.bean.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
