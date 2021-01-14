package com.app.dto;

import java.time.LocalDateTime;

public class ErrorResponse {

	private String message;
	private LocalDateTime time;
	private String description;

	public ErrorResponse() {
		System.out.println("In Constroctor of ErrorResponse ");
	}

	public ErrorResponse(String message, String description) {
		super();
		this.message = message;
		this.time = LocalDateTime.now();
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
