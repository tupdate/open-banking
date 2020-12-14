package com.cts.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
