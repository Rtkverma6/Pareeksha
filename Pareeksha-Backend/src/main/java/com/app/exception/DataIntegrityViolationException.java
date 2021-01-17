package com.app.exception;


@SuppressWarnings("serial")
public class DataIntegrityViolationException extends RuntimeException {

	public DataIntegrityViolationException(String mesg) {
		super(mesg);
	}
}
