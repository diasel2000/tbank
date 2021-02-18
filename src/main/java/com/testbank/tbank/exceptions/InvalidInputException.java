package com.testbank.tbank.exceptions;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(String msg) {
		super(msg);
	}

	public InvalidInputException(String msg, Throwable t) {
		super(msg, t);
	}
}
