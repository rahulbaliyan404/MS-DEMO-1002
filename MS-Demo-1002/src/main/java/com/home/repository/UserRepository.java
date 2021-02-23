package com.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.bean.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
