package com.cts.thirdparty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.thirdparty.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByUsernameAndPassword(String username, String password);
}
