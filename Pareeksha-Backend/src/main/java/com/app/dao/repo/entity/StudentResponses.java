package com.app.dao.repo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student_responses")
public class StudentResponses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "response_id")
	private Long responseId;
	@OneToOne
	@JoinColumn(name = "question_id")
	@JsonIgnore
	private Questions question;
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	@OneToOne
	@JoinColumn(name="choice_id")
	private QuestionsChoices selectedChoice;
	
	public StudentResponses() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public StudentResponses(Long responseId) {
		super();
		this.responseId = responseId;
	}

	public Long getResponseId() {
		return responseId;
	}

	public void setResponseId(Long responseId) {
		this.responseId = responseId;
	}

	@JsonIgnore
	public Questions getQuestion() {
		return question;
	}

	@JsonProperty
	public void setQuestion(Questions question) {
		this.question = question;
	}

	@JsonIgnore
	public Student getStudent() {
		return student;
	}

	@JsonProperty
	public void setStudent(Student student) {
		this.student = student;
	}

	@JsonIgnore
	public QuestionsChoices getSelectedChoice() {
		return selectedChoice;
	}

	@JsonProperty
	public void setSelectedChoice(QuestionsChoices selectedChoice) {
		this.selectedChoice = selectedChoice;
	}

	@Override
	public String toString() {
		return "StudentResponses [responseId=" + responseId + "]";
	}
	
}
