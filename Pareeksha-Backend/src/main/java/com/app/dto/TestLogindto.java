package com.app.dto;

import javax.validation.constraints.NotBlank;

public class TestLogindto {

	private Long paperId;
	@NotBlank(message = "Paper password is mandatory")
	private String paperPassword;
	
	public TestLogindto() {
		System.out.println("In Constructor of "+getClass().getName());
	}

	public Long getPaperId() {
		return paperId;
	}

	public void setPaperId(Long paperId) {
		this.paperId = paperId;
	}

	public String getPaperPassword() {
		return paperPassword;
	}

	public void setPaperPassword(String paperPassword) {
		this.paperPassword = paperPassword;
	}

	@Override
	public String toString() {
		return "TestLogindto [paperId=" + paperId + ", paperPassword=" + paperPassword + "]";
	}
	
}
