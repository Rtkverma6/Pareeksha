package com.app.dto;

import javax.validation.constraints.Min;

public class QuestionRequestDTO {

	private String question;
	@Min(value = 1,message = "Minimum value for points should be 1")
	private int points;
	private Long paperId;
	
	public QuestionRequestDTO() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	@Override
	public String toString() {
		return "QuestionRequestDTO [question=" + question + ", points=" + points + ", paperId=" + paperId + "]";
	}
	
	
}
