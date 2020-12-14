package com.cts.thirdparty.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.thirdparty.exceptions.UserNotFoundException;
import com.cts.thirdparty.model.User;
import com.cts.thirdparty.service.UserService;
import com.cts.thirdparty.utility.Base64Utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	private BiPredicate<String, String> stringNullCheck = (s1, s2) -> s1 != null && s2 != null && !"".equals(s1)
			&& !"".equals(s2);

	@GetMapping("/name")
	public String getInfo() {
		return "ThirdParty Org Service is working!";
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			user.setPassword(Base64Utility.getEncodedString(user.getPassword()));
			user = userService.registerUser(user);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {
		Map<String, String> map = new HashMap<>();

		String jwtToken = "";

		try {
			if (stringNullCheck.test(user.getUsername(), user.getPassword())) {
				jwtToken = getToken(user.getUsername(), Base64Utility.getEncodedString(user.getPassword()));
				map.clear();
				map.put("message", "Successfully logged in ...");
				map.put("token", jwtToken);
			} else {
				new ResponseEntity<>("username/password is null or blank", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("token", null);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		try {
			user = userService.updateUser(id, user);

			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		} catch (UserNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {

		try {
			boolean flag = userService.deleteUser(id);

			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (UserNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		try {
			User user = userService.getUserByUsername(id);
			if (user == null) {
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}

		} catch (UserNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	public String getToken(String username, String password) throws Exception {

		if (username == null || password == null) {
			throw new ServletException("Please provide valid username and password");
		}

		User user = userService.findByUsernameAndPassword(username, password);

		boolean flag = user != null ? true : false;

		if (!flag) {
			throw new ServletException("Either username or password is wrong.");
		}

		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
	}
}
