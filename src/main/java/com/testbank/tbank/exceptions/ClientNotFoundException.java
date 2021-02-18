package com.testbank.tbank.exceptions;

public class ClientNotFoundException extends UnprocessableEntityException {

    private static final long serialVersionUID = 1L;

    public ClientNotFoundException(String msg) {
        super(msg);
    }

    public ClientNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

}
