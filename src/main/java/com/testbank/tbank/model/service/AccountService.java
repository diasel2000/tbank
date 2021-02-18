package com.testbank.tbank.model.service;

import com.testbank.tbank.model.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.Set;

@Service
public class AccountService extends CrudService<Account, Integer> {

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
}
