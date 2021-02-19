package com.testbank.tbank.controller.response;

import com.testbank.tbank.model.entity.Account;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AccountResponse extends BaseResponse {
    private String id;
    private Integer accountNum;
    private String type;
    private Integer balance;
}
