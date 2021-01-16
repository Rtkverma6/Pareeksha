package com.app.mapper;

import com.app.dto.QuestionRequestDTO;
import com.app.pojos.Questions;

public class QuestionMapper {

	public static Questions mapQuestionDtoToQuestionEntity(QuestionRequestDTO dto,Questions entity) {
		entity.setQuestion(dto.getQuestion());
		entity.setPoints(dto.getPoints());
		System.out.println("In Mapper method of Question");
		System.out.println(entity);
		return entity;
	}
}
