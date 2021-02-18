package com.testbank.tbank.exceptions;

public class DeletingEntityReferencedException extends Exception {

    private static final long serialVersionUID = 1L;

    public DeletingEntityReferencedException() {
        super();
    }

    public DeletingEntityReferencedException(String msg) {
        super(msg);
    }

    public DeletingEntityReferencedException(String msg, Throwable t) {
        super(msg, t);
    }
}
