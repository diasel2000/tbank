package com.testbank.tbank.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountRequest {

    @NotNull
    String clientId;
}
