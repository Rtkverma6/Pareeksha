package com.app.mapper;

import com.app.dto.ChoicesDTO;
import com.app.pojos.QuestionsChoices;

public class ChoicesMapper {

	public static QuestionsChoices mapQuestionDtoToQuestionEntity(ChoicesDTO dto,QuestionsChoices entity) {
		entity.setChoice(dto.getChoice());
		entity.setCorrect(dto.getIsCorrect());
		System.out.println("In Mapper method of Choice");
		System.out.println(entity);
		return entity;
	}
}
