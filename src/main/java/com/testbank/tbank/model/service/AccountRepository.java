package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {

     List<Account> getAccountsByClient_Id(String clientId);

     Account getAccountById(String accountId);

}