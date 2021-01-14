package com.app.service;

import java.util.Optional;

import com.app.pojos.Questions;

public interface IQuestionsService {

	public Questions createQuestion(Questions transientQuestion);
	
	public Optional<Questions> fetchQuestion(Long id);
}
