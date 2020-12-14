package com.cts.account.service;

import com.cts.account.exceptions.AccountNotFoundException;
import com.cts.account.model.Account;

public interface AccountService {
	Account openAccountRequest(Account account);

	Account updateAccount(String accountid, Account account) throws AccountNotFoundException;

	boolean deleteAccount(String accountid) throws AccountNotFoundException;

	Account getAccountByAccountid(String accountid) throws AccountNotFoundException;
}
