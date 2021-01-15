package com.app.cust_excs;


@SuppressWarnings("serial")
public class IllegalArgumentException extends RuntimeException{

	public IllegalArgumentException(String mesg) {
		super(mesg);
	}

}
