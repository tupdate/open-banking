package com.cts.thirdparty.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.thirdparty.dao.UserRepository;
import com.cts.thirdparty.exceptions.UserAlreadyExistsException;
import com.cts.thirdparty.exceptions.UserNotFoundException;
import com.cts.thirdparty.model.User;

/**
 * User service for user crud and authenticate operations
 *
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User registerUser(User user) throws UserAlreadyExistsException {
		user = userRepository.save(user);
		if (user == null) {
			throw new UserAlreadyExistsException("User already exist");
		} else {
			return user;
		}
	}

	public User updateUser(String username, User user) throws UserNotFoundException {
		userRepository.save(user);
		return userRepository.findById(username).get();
	}

	public boolean deleteUser(String username) throws UserNotFoundException {
		try {
			userRepository.deleteById(username);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	public User getUserByUsername(String username) throws UserNotFoundException {
		Optional<User> optional = userRepository.findById(username);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new UserNotFoundException("User "+username+ " does not exist");
		}
	}
}
