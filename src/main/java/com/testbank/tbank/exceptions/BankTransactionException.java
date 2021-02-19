package com.testbank.tbank.exceptions;

public class BankTransactionException extends Exception {

    private static final long serialVersionUID = 1L;

    public BankTransactionException(String msg) {
        super(msg);
    }

    public BankTransactionException(String msg, Throwable t) {
        super(msg, t);
    }

}
