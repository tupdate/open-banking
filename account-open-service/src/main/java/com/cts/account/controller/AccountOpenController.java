package com.cts.account.controller;

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

import com.cts.account.exceptions.AccountNotFoundException;
import com.cts.account.model.Account;
import com.cts.account.service.AccountService;

@RestController("/account")
public class AccountOpenController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountOpenController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("/name")
	public String getInfo() {
		return "Account Open Service is working!";
	}
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		try {
			account = accountService.openAccountRequest(account);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<Account>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Account>(account, HttpStatus.CREATED);
	}

	@PutMapping("/{accountid}")
	public ResponseEntity<Account> updateAccount(@PathVariable String accountid, @RequestBody Account account) {
		try {
			account = accountService.updateAccount(accountid, account);

			if (account == null) {
				return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Account>(account, HttpStatus.OK);
			}
		} catch (AccountNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{accountid}")
	public ResponseEntity<Void> deleteAccount(@PathVariable String accountid) {

		try {
			boolean flag = accountService.deleteAccount(accountid);

			if (flag) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}
		} catch (AccountNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{accountid}")
	public ResponseEntity<Account> getAccountByAccountid(@PathVariable String accountid) {
		try {
			Account user = accountService.getAccountByAccountid(accountid);
			if (user == null) {
				return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Account>(user, HttpStatus.OK);
			}

		} catch (AccountNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
	}
}
