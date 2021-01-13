package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paper_setter")
public class PaperSetter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="paper_setter_id")
	private Long paperSetterId;
	@Column(length=30)
	private String name;
	@Column(length = 30,unique = true)
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	@Column(length = 15)
	private String password;
	
	public PaperSetter() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public PaperSetter(Long paperSetterId, String name, String email, LocalDate dob, String password) {
		super();
		this.paperSetterId = paperSetterId;
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.password = password;
	}

	public Long getPaperSetterId() {
		return paperSetterId;
	}

	public void setPaperSetterId(Long paperSetterId) {
		this.paperSetterId = paperSetterId;
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
		return "PaperSetter [paperSetterId=" + paperSetterId + ", name=" + name + ", email=" + email + ", dob=" + dob
				+ ", password=" + password + "]";
	}
	
	
}
