package com.testbank.tbank.controller.request;

import com.testbank.tbank.model.entity.Account;
import lombok.Data;

import java.util.Set;

@Data
public class ClientRequest {

    private String id;

    private String firstName;

    private String lastName;

    private Set<Account> accaunts;

}
