package com.app.service;

import java.util.ArrayList;

import com.app.dao.entity.QuestionsChoices;

public interface IChoicesService {

	public QuestionsChoices insertChoice(QuestionsChoices transientChoice);
	
	public ArrayList<QuestionsChoices> fetchChoices(Long questionId);
}
