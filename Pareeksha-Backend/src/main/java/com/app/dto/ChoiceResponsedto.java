package com.app.dto;

public class ChoiceResponsedto {
	
	private Long choiceId;
	private String choice;
	private boolean isCorrect;
	
	public ChoiceResponsedto() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public ChoiceResponsedto(Long choiceId, String choice, boolean isCorrect) {
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

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "ChoiceResponsedto [choiceId=" + choiceId + ", choice=" + choice + ", isCorrect=" + isCorrect + "]";
	}
	
}
