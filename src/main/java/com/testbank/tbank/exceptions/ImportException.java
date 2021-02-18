package com.testbank.tbank.exceptions;

public class ImportException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public ImportException() {
        super();
    }

	public ImportException(String msg) {
		super(msg);
	}

	public ImportException(String msg, Throwable t) {
		super(msg, t);
	}
	
}
