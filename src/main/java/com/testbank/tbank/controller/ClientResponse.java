package com.testbank.tbank.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class ClientResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    private String clientId;
}
