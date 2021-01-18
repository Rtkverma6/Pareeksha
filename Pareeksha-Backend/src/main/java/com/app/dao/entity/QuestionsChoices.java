package com.app.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "questions_choices")
public class QuestionsChoices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "choice_id")
	private Long choiceId;
	@Column(length = 255)
	private String choice;
	@ManyToOne
	@JoinColumn(name="question_id")
	private Questions question;
	@Column(columnDefinition = "boolean default false")
	private boolean isCorrect;
	
	public QuestionsChoices() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public QuestionsChoices(Long choiceId, String choice, boolean isCorrect) {
		super();
		this.choiceId = choiceId;
		this.choice = choice;
		this.isCorrect = isCorrect;
	}

	public Long getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(Long choiceId) {
		this.choiceId = choiceId;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	@JsonIgnore
	public Questions getQuestion() {
		return question;
	}

	@JsonProperty
	public void setQuestion(Questions question) {
		this.question = question;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "QuestionsChoices [choiceId=" + choiceId + ", choice=" + choice + ", isCorrect=" + isCorrect + "]";
	}
	
}
