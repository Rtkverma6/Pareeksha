package com.app.dto;

import java.time.LocalDateTime;

public class PaperResultdto {
	private Long studentId;
	private int marksObtained;
	private Long paperId;
	private LocalDateTime submittedOn;

	public PaperResultdto() {
		System.out.println("In Constructor of " + getClass().getName());
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public LocalDateTime getSubmittedOn() {
		return submittedOn;
	}

	public void setSubmittedOn(LocalDateTime submittedOn) {
		this.submittedOn = submittedOn;
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	@Override
	public String toString() {
		return "PaperResultdto [studentId=" + studentId + ", marksObtained=" + marksObtained + ", paperId=" + paperId
				+ ", submittedOn=" + submittedOn + "]";
	}

}
