package com.app.dto;

import java.util.ArrayList;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionRequestdto {

	private String question;
	@Min(value = 1,message = "Minimum value for points should be 1")
	private int points;
	private Long paperId;
	@JsonProperty
	private ArrayList<Choicesdto> choices = new ArrayList<>();
	
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
		return "QuestionRequestdto [question=" + question + ", points=" + points + ", paperId=" + paperId + ", choices="
				+ choices + "]";
	}
}
