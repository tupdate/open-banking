package com.cts.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountOpenController {

	@GetMapping("/")
	public String getInfo() {
		return "Account Open Service is working!";
	}
}
