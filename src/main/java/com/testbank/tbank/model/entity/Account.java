package com.testbank.tbank.model.entity;

import javax.persistence.*;

import com.testbank.tbank.model.AbstractEntity;
import lombok.Data;

@Entity
public class Account extends AbstractEntity {

    private Integer accountNum;
    private String type;
    private Integer balance;
    @ManyToOne(targetEntity = Client.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    public Integer getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Integer accountNum) {
        this.accountNum = accountNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }


    @JoinColumn(name = "id", referencedColumnName = "acc_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
