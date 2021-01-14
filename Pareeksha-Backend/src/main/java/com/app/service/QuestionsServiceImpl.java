package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.QuestionsRepo;
import com.app.pojos.Questions;

@Service
@Transactional
public class QuestionsServiceImpl implements IQuestionsService {

	@Autowired
	QuestionsRepo repo;
	
	@Override
	public Questions createQuestion(Questions transientQuestion) {
		Questions persistentQuestion = repo.save(transientQuestion);
		return persistentQuestion;
	}

	@Override
	public Optional<Questions> fetchQuestion(Long id) {
		return repo.findById(id);
	}

}
