package com.app.service;

import java.util.ArrayList;
import java.util.Optional;

import com.app.dao.entity.Questions;

public interface IQuestionsService {

	public Questions createQuestion(Questions transientQuestion);
	
	public Optional<Questions> fetchQuestion(Long id);
	
	public ArrayList<Questions> fetchAllQuestions(long paperId);
}
