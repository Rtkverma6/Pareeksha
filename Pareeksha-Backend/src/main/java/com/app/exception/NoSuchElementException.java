package com.app.exception;

@SuppressWarnings("serial")
public class NoSuchElementException extends RuntimeException{

	public NoSuchElementException(String mesg) {
		super(mesg);
	}
}
