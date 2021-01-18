package com.app.dto;

import javax.validation.constraints.NotBlank;

public class PaperLogindto {

	private Long paperId;
	@NotBlank(message = "Paper password is mandatory")
	private String paperPassword;
	
	public PaperLogindto() {
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
		return "PaperLogindto [paperId=" + paperId + ", paperPassword=" + paperPassword + "]";
	}
	
}
