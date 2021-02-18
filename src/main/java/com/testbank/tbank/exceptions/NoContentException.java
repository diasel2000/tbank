package com.testbank.tbank.exceptions;

public class NoContentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoContentException() {
        super();
    }

    public NoContentException(String msg) {
        super(msg);
    }

    public NoContentException(String msg, Throwable t) {
        super(msg, t);
    }
}
