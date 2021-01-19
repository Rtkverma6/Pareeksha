package com.app.dao.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long questionId;
	@Column(columnDefinition = "TEXT")
	private String question;
	@Lob
	private byte[] image;
	@Column(length = 30)
	private String imageContentType;
	private int points;
	@ManyToOne
	@JoinColumn(name="paper_id")
	private Paper paper;
	@Enumerated(EnumType.STRING)
	@Column(name="question_type",length =20)
	private QuestionType questionType;
	
	public Questions() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public Questions(Long questionId, String question, byte[] image, String imageContentType, int points,
			QuestionType questionType) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.image = image;
		this.imageContentType = imageContentType;
		this.points = points;
		this.questionType = questionType;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return "Questions [questionId=" + questionId + ", question=" + question + ", image=" + Arrays.toString(image)
				+ ", imageContentType=" + imageContentType + ", points=" + points + ", questionType=" + questionType
				+ "]";
	}

		
}
