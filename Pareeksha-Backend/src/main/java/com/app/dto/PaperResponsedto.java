package com.app.dto;

import java.time.LocalTime;
import java.util.ArrayList;

public class PaperResponsedto {

	private String paperName;
	private String paperSubject;
	private LocalTime duration;
	private String difficultyLevel;
	private ArrayList<QuestionResponsedto> questions = new ArrayList<>();
	
	public PaperResponsedto() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getPaperSubject() {
		return paperSubject;
	}

	public void setPaperSubject(String paperSubject) {
		this.paperSubject = paperSubject;
	}

	public ArrayList<QuestionResponsedto> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<QuestionResponsedto> questions) {
		this.questions = questions;
	}
	
	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	//Helper Method to add Question in PaperResponseDto
	public void addQuestion(QuestionResponsedto question) {
		System.out.println("In addQuestion()");
		System.out.println(question);
		questions.add(question);
	}

	@Override
	public String toString() {
		return "PaperResponsedto [paperName=" + paperName + ", paperSubject=" + paperSubject + ", duration=" + duration
				+", difficultyLevel=" + difficultyLevel + ", questions="
				+ questions + "]";
	}
	
}
