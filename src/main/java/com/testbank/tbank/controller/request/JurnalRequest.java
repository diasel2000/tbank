package com.testbank.tbank.controller.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class JurnalRequest {
    private String payerId;
    private String recipientId;
    private String sourceId;
    private String destId;
}
