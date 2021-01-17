package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ChoicesRepo;
import com.app.dao.entity.QuestionsChoices;

@Service
@Transactional
public class ChoiceServiceImpl implements IChoicesService {

	@Autowired
	ChoicesRepo repo;
	
	@Override
	public QuestionsChoices insertChoice(QuestionsChoices transientChoice) {
		return repo.save(transientChoice);
	}

}
