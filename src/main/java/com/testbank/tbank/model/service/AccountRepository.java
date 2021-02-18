package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}