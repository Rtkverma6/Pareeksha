package com.app.mapper;

import com.app.dao.entity.QuestionsChoices;
import com.app.dto.Choicesdto;

public class ChoicesMapper {

	public static QuestionsChoices mapQuestionDtoToQuestionEntity(Choicesdto dto,QuestionsChoices entity) {
		entity.setChoice(dto.getChoice());
		entity.setCorrect(dto.getIsCorrect());
		System.out.println("In Mapper method of Choice");
		System.out.println(entity);
		return entity;
	}
}
