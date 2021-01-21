package com.app.dto;

import java.util.ArrayList;

public class PaperResponsedto {

	private Long paperId;
	private String paperName;
	private String paperSubject;
	private int duration;
	private int totalMarks;
	private int totalQuestions;
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
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	//Helper Method to add Question in PaperResponseDto
	public void addQuestion(QuestionResponsedto question) {
		System.out.println("In addQuestion()");
		System.out.println(question);
		questions.add(question);
	}

	@Override
	public String toString() {
		return "PaperResponsedto [paperId=" + paperId + ", paperName=" + paperName + ", paperSubject=" + paperSubject
				+ ", duration=" + duration + ", totalMarks=" + totalMarks + ", totalQuestions=" + totalQuestions
				+ ", difficultyLevel=" + difficultyLevel + ", questions=" + questions + "]";
	}
	
}
