package com.testbank.tbank.exceptions;

public class EntryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public EntryNotFoundException() {
        super();
    }

	public EntryNotFoundException(String msg) {
		super(msg);
	}

	public EntryNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
