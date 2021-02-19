package com.testbank.tbank.controller.response;

import com.testbank.tbank.controller.response.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String clientId;
}
