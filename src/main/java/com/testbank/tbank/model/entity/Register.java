package com.testbank.tbank.model.entity;

import javax.persistence.Entity;

import com.testbank.tbank.model.AbstractEntity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Register extends AbstractEntity {

    private Integer paymentId;
    private String timestamp;
    private String sorceId;
    private String destId;
    private Integer amount;
    private Integer clientPayer;
    private Integer clientRecipient;

    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getSorceId() {
        return sorceId;
    }
    public void setSorceId(String sorceId) {
        this.sorceId = sorceId;
    }
    public String getDestId() {
        return destId;
    }
    public void setDestId(String destId) {
        this.destId = destId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getClientPayer() {
        return clientPayer;
    }
    public void setClientPayer(Integer clientPayer) {
        this.clientPayer = clientPayer;
    }
    public Integer getClientRecipient() {
        return clientRecipient;
    }
    public void setClientRecipient(Integer clientRecipient) {
        this.clientRecipient = clientRecipient;
    }

}
