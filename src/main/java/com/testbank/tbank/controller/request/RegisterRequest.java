package com.testbank.tbank.controller.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {
    private LocalDate timestamp;
    private String sorceId;
    private String destId;
    private Integer amount;
    private String reason;

}
