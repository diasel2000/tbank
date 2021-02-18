package com.testbank.tbank.exceptions;

public class InvalidResponseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidResponseException() {
        super();
    }

    public InvalidResponseException(String msg) {
        super(msg);
    }

    public InvalidResponseException(String msg, Throwable t) {
        super(msg, t);
    }

}
