package com.cts.auth.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cts.auth.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);
}