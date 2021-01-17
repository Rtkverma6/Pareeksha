package com.app.dao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;
	@Column(length = 12)
	private String prn; 
	private int marks;
	@Column(name = "student_name",length = 30)
	private String studentName;
	@ManyToOne
	@JoinColumn(name="paper_id")
	@JsonIgnore
	private Paper paper;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime submittedOn;
	
	public Student() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public Student(Long studentId, String prn, int marks, String studentName, LocalDateTime submittedOn) {
		super();
		this.studentId = studentId;
		this.prn = prn;
		this.marks = marks;
		this.studentName = studentName;
		this.submittedOn = submittedOn;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getPrn() {
		return prn;
	}

	public void setPrn(String prn) {
		this.prn = prn;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@JsonIgnore
	public Paper getPaper() {
		return paper;
	}

	@JsonProperty
	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public LocalDateTime getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(LocalDateTime submittedOn) {
		this.submittedOn = submittedOn;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", prn=" + prn + ", marks=" + marks + ", studentName=" + studentName
				+ ", submittedOn=" + submittedOn + "]";
	}
	
}
