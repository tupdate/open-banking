package com.cts.account.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.account.dao.AccountRepository;
import com.cts.account.exceptions.AccountNotFoundException;
import com.cts.account.model.Account;

@Service
public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	private AccountRepository userRepository;

	@Autowired
	public AccountServiceImpl(AccountRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Account openAccountRequest(Account account) {
		account = userRepository.save(account);

		return account;
	}

	public Account updateAccount(String username, Account account) throws AccountNotFoundException {
		userRepository.save(account);

		return userRepository.findById(username).get();
	}

	public boolean deleteAccount(String username) throws AccountNotFoundException {
		try {
			userRepository.deleteById(username);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	public Account getAccountByAccountid(String accountid) throws AccountNotFoundException {
		Optional<Account> optional = userRepository.findById(accountid);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new AccountNotFoundException("Account " + accountid + " does not exist");
		}
	}
}
