package com.cts.open.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.open.account.objects.RetirementAccount;

@RestController
public class AccountOpenController {

	@GetMapping("/{id}")
	public RetirementAccount getAccount(@PathVariable String id) {
		return new RetirementAccount("RA000"+id, "Pankaj", "Kumar");
	}
	
	@GetMapping
	public String getInfo() {
		return "It's working!";
	}
}
