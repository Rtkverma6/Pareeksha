package com.app.mapper;

import java.util.ArrayList;

import com.app.dao.entity.Questions;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.QuestionRequestdto;
import com.app.dto.QuestionResponsedto;

public class QuestionMapper {

	public static Questions mapQuestionDtoToQuestionEntity(QuestionRequestdto dto, Questions entity) {
		entity.setQuestion(dto.getQuestion());
		entity.setPoints(dto.getPoints());
		System.out.println("In Mapper method of Question");
		System.out.println(entity);
		return entity;
	}

	public static QuestionResponsedto mapQuestionEntityToQuestionResponseDto(Questions entity,
			QuestionResponsedto dto,ArrayList<ChoiceResponsedto> choicedto) {
		System.out.println("In mapQuestionEntityToQuestionResponseDto()");
		System.out.println(entity);
		dto.setQuestion(entity.getQuestion());
		dto.setQuestionId(entity.getQuestionId());
		dto.setPoints(entity.getPoints());
		dto.setChoices(choicedto);
		System.out.println("Printing QuestionResponsedto");
		System.out.println(dto);
		return dto;
	}
}
