package com.cts.balance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountBalanceController {

	@GetMapping("/name")
	public String getInfo() {
		return "Account Balance Service is working!";
	}
}
