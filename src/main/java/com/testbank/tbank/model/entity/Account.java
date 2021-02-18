package com.testbank.tbank.model.entity;

import javax.persistence.Entity;

import com.testbank.tbank.model.AbstractEntity;
import lombok.Data;

@Entity
@Data
public class Account extends AbstractEntity {

    private String accountId;
    private Integer accountNum;
    private String type;
    private Integer balance;

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
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

}
