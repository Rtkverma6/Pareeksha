package com.app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ChoicesRepo;
import com.app.dao.entity.QuestionsChoices;

@Service
public class ChoiceServiceImpl implements IChoicesService {

	@Autowired
	ChoicesRepo repo;
	
	@Override
	public ArrayList<QuestionsChoices> fetchChoices(Long questionId) {
		System.out.println("In fetchChoices()    "+questionId);
		ArrayList<QuestionsChoices> fetchChoices = repo.fetchChoices(questionId);
		System.out.println(fetchChoices);
		return fetchChoices;
	}

	@Override
	public QuestionsChoices insertChoice(QuestionsChoices transientChoice) {
		transientChoice.setChoiceId(null); 
		return  repo.save(transientChoice);
	}

}
