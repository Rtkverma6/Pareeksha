package com.app.dto;

import javax.validation.constraints.NotBlank;

public class ChoicesDTO {

	@NotBlank(message = "Please Provide test for given choice")
	private String choice;
	private Long questionId;
	private boolean isCorrect;
	
	public ChoicesDTO() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "ChoicesDTO [choice=" + choice + ", questionId=" + questionId + ", isCorrect=" + isCorrect + "]";
	}
	
}
