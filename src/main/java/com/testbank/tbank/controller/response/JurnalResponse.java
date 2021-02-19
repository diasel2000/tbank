package com.testbank.tbank.controller.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.testbank.tbank.model.entity.Client;
import com.testbank.tbank.model.entity.Register;
import lombok.Data;

import java.util.List;

@Data
public class JurnalResponse extends BaseResponse {

    @JsonView(BaseResponse.class)
    private Register register;

    @JsonView(BaseResponse.class)
    private List<Client> clients;

}
