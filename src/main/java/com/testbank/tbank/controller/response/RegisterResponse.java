package com.testbank.tbank.controller.response;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

@Data
public class RegisterResponse extends BaseResponse {


    @JsonView(BaseResponse.class)
    String paymentId;
}
