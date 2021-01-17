package com.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StudentDetailsdto {

	@Size(max = 6,min = 6,message = "PRN should be of 6 characters")
	private String prn;
	@NotBlank(message = "Student name is mandatory ")
	private String studentName;
	private Long paperId;
	
	public StudentDetailsdto() {
		System.out.println("In Construuctor of "+getClass().getName());
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	@Override
	public String toString() {
		return "StudentDetailsdto [prn=" + prn + ", studentName=" + studentName + ", paperId=" + paperId + "]";
	}

	
	
	
}
