package com.testbank.tbank.exceptions;

public class EntryInconsistencyException extends Exception {

    private static final long serialVersionUID = 1L;

    public EntryInconsistencyException() {
        super();
    }

    public EntryInconsistencyException(String msg) {
        super(msg);
    }

    public EntryInconsistencyException(String msg, Throwable t) {
        super(msg, t);
    }
}
