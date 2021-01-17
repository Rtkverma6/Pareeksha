package com.app.exception;


@SuppressWarnings("serial")
public class IllegalArgumentException extends RuntimeException{

	public IllegalArgumentException(String mesg) {
		super(mesg);
	}

}
