package com.cts.thirdparty.service;

import com.cts.thirdparty.model.User;
import com.cts.thirdpartyl.exceptions.UserAlreadyExistsException;
import com.cts.thirdpartyl.exceptions.UserNotFoundException;

public interface UserService {
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;

	User registerUser(User user) throws UserAlreadyExistsException;

	User updateUser(String username, User user) throws UserNotFoundException;

	boolean deleteUser(String username) throws UserNotFoundException;

	User getUserByUsername(String username) throws UserNotFoundException;
}
