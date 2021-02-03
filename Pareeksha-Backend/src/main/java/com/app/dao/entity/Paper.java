package com.app.dao.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "paper")
public class Paper {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paper_id")
	private Long paperId;
	@Column(name = "paper_name", length = 30)
	private String paperName;
	@Column(name = "paper_subject", length = 25)
	private String paperSubject;
	@OneToOne
	@JoinColumn(name = "paper_setter_id")
	private PaperSetter paperSetter;
	@Column(columnDefinition = " boolean default false")
	private boolean reviewed;
	@Column(length = 10)
	private String paperPassword;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime startDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime endDate;
	@DateTimeFormat(iso = ISO.TIME)
	private LocalTime duration;
	@Enumerated(EnumType.STRING)
	@Column(name = "difficulty_level", length = 10)
	private PaperDiffucultyLevel difficultyLevel;
	@Column(name = "total_marks")
	private int totalMarks;
	@Column(name = "total_questions")
	private int totalQuestions;

	public Paper() {
		System.out.println("In Constructor of " + getClass().getName());
	}

	public Paper(Long paperId, String paperName, String paperSubject, boolean reviewed, String paperPassword,
			LocalDateTime startDate, LocalDateTime endDate, LocalTime duration, PaperDiffucultyLevel difficultyLevel,
			int totalMarks, int totalQuestions) {
		super();
		this.paperId = paperId;
		this.paperName = paperName;
		this.paperSubject = paperSubject;
		this.reviewed = reviewed;
		this.paperPassword = paperPassword;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
		this.difficultyLevel = difficultyLevel;
		this.totalMarks = totalMarks;
		this.totalQuestions = totalQuestions;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
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

	public PaperSetter getPaperSetter() {
		return paperSetter;
	}

	public void setPaperSetter(PaperSetter paperSetter) {
		this.paperSetter = paperSetter;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public String getPaperPassword() {
		return paperPassword;
	}

	public void setPaperPassword(String paperPassword) {
		this.paperPassword = paperPassword;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalTime getDuration() {
		return duration;
	}

	public void setDuration(LocalTime duration) {
		this.duration = duration;
	}

	public PaperDiffucultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(PaperDiffucultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
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

	@Override
	public String toString() {
		return "Paper [paperId=" + paperId + ", paperName=" + paperName + ", paperSubject=" + paperSubject
				+ ", reviewed=" + reviewed + ", paperPassword=" + paperPassword + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", duration=" + duration + ", difficultyLevel=" + difficultyLevel
				+ ", totalMarks=" + totalMarks + ", totalQuestions=" + totalQuestions + "]";
	}

}
