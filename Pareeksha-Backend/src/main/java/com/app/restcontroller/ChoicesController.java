package com.app.restcontroller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dto.Choicesdto;
import com.app.mapper.ChoicesMapper;
import com.app.service.IChoicesService;
import com.app.service.IQuestionsService;

public class ChoicesController {

	@Autowired
	IQuestionsService questionService;
	@Autowired
	IChoicesService choiceService;

	public ArrayList<QuestionsChoices> insertChoice(@Valid ArrayList<Choicesdto> choicesDto, QuestionsChoices transientChoice,
			Questions detachedQuestion) {
		System.out.println("In insertChoice()");
		ArrayList<QuestionsChoices> insertedChoices = new ArrayList<>();
		for (Choicesdto choice : choicesDto) {
			transientChoice = ChoicesMapper.mapChoiceDtoToQuestionsChoicesEntity(choice, transientChoice);
			transientChoice.setQuestion(detachedQuestion);
			QuestionsChoices insertChoice = choiceService.insertChoice(transientChoice);
			if (insertedChoices == null) {
				throw new RuntimeException("Error accords while saving questions choices");
			}
			insertedChoices.add(insertChoice);
		}
		return insertedChoices;
	}
}
