package com.app.cust_excs;

@SuppressWarnings("serial")
public class NoSuchElementException extends RuntimeException{

	public NoSuchElementException(String mesg) {
		super(mesg);
	}
}
