package com.app.mapper;

import com.app.dao.entity.Questions;
import com.app.dto.QuestionRequestdto;

public class QuestionMapper {

	public static Questions mapQuestionDtoToQuestionEntity(QuestionRequestdto dto,Questions entity) {
		entity.setQuestion(dto.getQuestion());
		entity.setPoints(dto.getPoints());
		System.out.println("In Mapper method of Question");
		System.out.println(entity);
		return entity;
	}
}
