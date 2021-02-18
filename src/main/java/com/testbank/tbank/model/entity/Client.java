package com.testbank.tbank.model.entity;

import javax.persistence.Entity;

import com.testbank.tbank.model.AbstractEntity;
import lombok.Data;


@Entity
@Data
public class Client extends AbstractEntity {

    private String accId;
    private String firstName;
    private String lastName;

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

}
