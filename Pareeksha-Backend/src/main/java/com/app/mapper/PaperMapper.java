package com.app.mapper;

import com.app.dao.entity.Paper;
import com.app.dto.PaperRequestdto;

public class PaperMapper {

	public static Paper mapPaperDtoToPaperEntity(PaperRequestdto dto,Paper entity) {
		entity.setPaperName(dto.getPaperName());
		entity.setPaperSubject(dto.getPaperSubject());
		entity.setPaperPassword(dto.getPaperPassword());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setDuration(dto.getDuration());
		entity.setDifficultyLevel(dto.getDifficultyLevel());
		System.out.println("In Mapper method of Paper");
		System.out.println(entity);
		return entity;
	}
}
