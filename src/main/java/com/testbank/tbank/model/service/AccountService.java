package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountService extends CrudService<Account, String> {

    private AccountRepository repository;

    public AccountService(@Autowired AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    protected AccountRepository getRepository() {
        return repository;
    }

    public void save(Set<Account> accountSet) {
        if (!accountSet.isEmpty())
            getRepository().saveAll(accountSet);
    }

    public List<Account> getAccountsByClientId(String clientId){
        return repository.getAccountsByClient_Id(clientId);
    }

    public Account findById(String accountId){
        return repository.getAccountById(accountId);
    }
}
