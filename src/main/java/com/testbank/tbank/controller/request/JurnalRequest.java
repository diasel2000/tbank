package com.testbank.tbank.controller.request;

import lombok.Data;

@Data
public class JurnalRequest {
    private String payerId;
    private String recipientId;
    private String sourceId;
    private String destId;
}
