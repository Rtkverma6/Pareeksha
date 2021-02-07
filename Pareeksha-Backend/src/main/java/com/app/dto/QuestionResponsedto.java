package com.app.dto;

import java.util.ArrayList;

public class QuestionResponsedto {
	
	private Long questionId;
    private String question;
    private int points;
	private String questionType;
    private ArrayList<ChoiceResponsedto> choices = new ArrayList<>();
    
    public QuestionResponsedto() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
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

	public ArrayList<ChoiceResponsedto> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<ChoiceResponsedto> choices) {
		this.choices = choices;
	}
	
	
	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public void addChoice(ChoiceResponsedto choice) {
		choices.add(choice);
	}

	@Override
	public String toString() {
		return "QuestionResponsedto [questionId=" + questionId + ", question=" + question + ", points=" + points
				+ ", questionType=" + questionType + ", choices=" + choices + "]";
	}
    
    
}
