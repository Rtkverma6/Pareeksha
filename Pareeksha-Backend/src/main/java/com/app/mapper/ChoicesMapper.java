package com.app.mapper;

import java.util.ArrayList;

import com.app.dao.entity.QuestionsChoices;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.Choicesdto;

public class ChoicesMapper {

	public static QuestionsChoices mapChoiceDtoToQuestionsChoicesEntity(Choicesdto dto, QuestionsChoices entity) {
		entity.setChoice(dto.getChoice());
		entity.setCorrect(dto.getIsCorrect());
		System.out.println("In Mapper method of Choice");
		System.out.println(entity);
		return entity;
	}

	public static ArrayList<ChoiceResponsedto> mapChoiceEntityToChoiceResponseDto(ArrayList<QuestionsChoices> entity,
			ArrayList<ChoiceResponsedto> dto) {

		for (QuestionsChoices questionsChoice : entity) {
			dto.add(new ChoiceResponsedto(questionsChoice.getChoiceId(), questionsChoice.getChoice(),
					questionsChoice.isCorrect()));
		}

		return dto;
	}
}
