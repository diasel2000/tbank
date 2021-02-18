package com.testbank.tbank.exceptions;

public class UnprocessableEntityException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnprocessableEntityException() {
        super();
    }

    public UnprocessableEntityException(String msg) {
        super(msg);
    }

    public UnprocessableEntityException(String msg, Throwable t) {
        super(msg, t);
    }
}
