package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaperSetterDto {
	
	@NotBlank(message = "Name must be provided")
	private String name;
	@Email(message = "Please provide valid email address")
	private String email;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;
	@Size(max = 15,min = 8,message = "Password must be of between 8 to 15 character")
	private String password;
	
	public PaperSetterDto() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public PaperSetterDto(String name, String email, LocalDate dob, String password) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PaperSetterDto [name=" + name + ", email=" + email + ", dob=" + dob + ", password=" + password + "]";
	}
	
	
	
}
