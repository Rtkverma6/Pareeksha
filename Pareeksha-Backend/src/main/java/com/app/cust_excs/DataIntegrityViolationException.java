package com.app.cust_excs;


@SuppressWarnings("serial")
public class DataIntegrityViolationException extends RuntimeException {

	public DataIntegrityViolationException(String mesg) {
		super(mesg);
	}
}
