package com.testbank.tbank.controller.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class AccountResponse extends BaseResponse {

    @JsonView(BaseResponse.class)
    private String id;

    @JsonView(BaseResponse.class)
    private Integer accountNum;

    @JsonView(BaseResponse.class)
    private String type;

    @JsonView(BaseResponse.class)
    private Integer balance;
}
