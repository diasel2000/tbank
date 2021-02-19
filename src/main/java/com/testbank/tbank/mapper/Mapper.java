package com.testbank.tbank.mapper;

import com.testbank.tbank.controller.response.AccountResponse;
import com.testbank.tbank.exceptions.BankTransactionException;
import com.testbank.tbank.model.entity.Account;
import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.entity.Register;
import com.testbank.tbank.model.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class Mapper {

    @Autowired
    private AccountService accountService;

    public List<AccountResponse> mapAccount(List<Account> accounts) {
        List<AccountResponse> responseList = new LinkedList<>();
        for (Account account : accounts) {
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(account.getId());
            accountResponse.setAccountNum(account.getAccountNum());
            accountResponse.setType(account.getType());
            accountResponse.setBalance(account.getBalance());
            responseList.add(accountResponse);
        }
        return responseList;
    }

    public Client mapClient(String id, String firstName, String lastName, Set<Account> accaunts) {
        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        for (Account account : accaunts) {
            client.addAccount(account);
        }
        return client;
    }

    public Register mapRegister(String s, String sorceId, String destId, Integer amount){
        Register register = new Register();
        register.setTimestamp(s);
        register.setSorceId(sorceId);
        register.setDestId(destId);
        register.setAmount(amount);
        return register;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(String id, Integer amount) throws BankTransactionException {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found " + id);
        }
        Integer newBalance = account.getBalance() + amount;
        if (account.getBalance() + amount < 0) {
            throw new BankTransactionException(
                    "The money in the account '" + id + "' is not enough (" + account.getBalance() + ")");
        }
        account.setBalance(newBalance);
    }
}
