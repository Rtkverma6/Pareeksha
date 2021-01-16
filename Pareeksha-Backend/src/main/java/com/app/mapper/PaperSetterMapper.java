package com.app.mapper;

import com.app.dto.PaperSetterDto;
import com.app.pojos.PaperSetter;

public class PaperSetterMapper {

	public static PaperSetter mapPaperSetterDtoToPapersetterEntity(PaperSetterDto dto,PaperSetter entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDob(dto.getDob());
		entity.setPassword(dto.getPassword());
		System.out.println("In Mapper method of PaperSetter");
		System.out.println(entity);
		return entity;
	}
}
