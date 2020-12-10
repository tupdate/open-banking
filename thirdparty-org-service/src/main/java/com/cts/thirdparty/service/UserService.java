package com.cts.thirdparty.service;

import com.cts.thirdparty.exceptions.UserAlreadyExistsException;
import com.cts.thirdparty.exceptions.UserNotFoundException;
import com.cts.thirdparty.model.User;

public interface UserService {
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;

	User registerUser(User user) throws UserAlreadyExistsException;

	User updateUser(String username, User user) throws UserNotFoundException;

	boolean deleteUser(String username) throws UserNotFoundException;

	User getUserByUsername(String username) throws UserNotFoundException;
}
