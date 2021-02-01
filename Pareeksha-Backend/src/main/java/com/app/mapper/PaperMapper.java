package com.app.mapper;

import com.app.dao.repo.entity.Paper;
import com.app.dao.repo.entity.PaperDiffucultyLevel;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;

public class PaperMapper {

	public static Paper mapPaperDtoToPaperEntity(PaperRequestdto dto,Paper entity) {
		entity.setPaperName(dto.getPaperName());
		entity.setPaperSubject(dto.getPaperSubject());
		entity.setPaperPassword(dto.getPaperPassword());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setDuration(dto.getDuration());
		entity.setDifficultyLevel(PaperDiffucultyLevel.valueOf(dto.getDifficultyLevel()));
		entity.setTotalMarks(dto.getTotalMarks());
		entity.setTotalQuestions(dto.getTotalQuestions());
		System.out.println("In Mapper method of Paper");
		System.out.println(entity);
		return entity;
	}
	
	public static PaperResponsedto mapPaperEntityToPaperDto(Paper entity,PaperResponsedto dto) {
		dto.setPaperId(entity.getPaperId());
		dto.setPaperName(entity.getPaperName());
		dto.setPaperSubject(entity.getPaperSubject());
		dto.setDifficultyLevel(entity.getDifficultyLevel().toString());
		dto.setTotalMarks(entity.getTotalMarks());
		dto.setTotalQuestions(entity.getTotalQuestions());
		int hour = entity.getDuration().getHour();
		int minute = entity.getDuration().getMinute();
		dto.setDuration((hour * 60 *60 )+(minute *60));
		return dto;
	}
}
