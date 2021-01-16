package com.app.mapper;

import com.app.dto.PaperRequestDTO;
import com.app.pojos.Paper;

public class PaperMapper {

	public static Paper mapPaperDtoToPaperEntity(PaperRequestDTO dto,Paper entity) {
		entity.setPaperName(dto.getPaperName());
		entity.setPaperSubject(dto.getPaperSubject());
		entity.setPaperPassword(dto.getPaperPassword());
		entity.setStartDate(dto.getStartDate());
		entity.setEndDate(dto.getEndDate());
		entity.setDuration(dto.getDuration());
		System.out.println("In Mapper method of Paper");
		System.out.println(entity);
		return entity;
	}
}
