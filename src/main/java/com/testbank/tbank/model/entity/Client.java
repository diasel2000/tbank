package com.testbank.tbank.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.testbank.tbank.model.AbstractEntity;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Client extends AbstractEntity {

    private String accId;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private Set<Account> accounts = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccId() {
        return accId;
    }

    public void setAccId(String accId) {
        this.accId = accId;
    }

    public Set<Account> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(Set<Account> account) {
        this.accounts = account;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        this.accounts.add(account);
    }
}
