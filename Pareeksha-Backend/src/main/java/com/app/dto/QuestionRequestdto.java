package com.app.dto;

import java.util.ArrayList;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequestdto {

	private String question;
	@Min(value = 1,message = "Minimum value for points should be 1")
	private int points;
	private Long paperId;
	@NotBlank(message = "Please provide question Type")
	@Size(max=20,message ="Max Length of paper Type is 20 characters")
	private String questionType;
	@JsonProperty
	private ArrayList<Choicesdto> choices = new ArrayList<>();
	
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public QuestionRequestdto() {
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

	@JsonIgnore
	public ArrayList<Choicesdto> getChoices() {
		return choices;
	}

	@JsonProperty
	public void setChoices(ArrayList<Choicesdto> choices) {
		this.choices=choices;
	}

	@Override
	public String toString() {
		return "QuestionRequestdto [question=" + question + ", points=" + points + ", paperId=" + paperId
				+ ", questionType=" + questionType + ", choices=" + choices + "]";
	}
}
