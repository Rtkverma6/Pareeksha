package com.app.mapper;

import com.app.dao.entity.Student;
import com.app.dto.StudentDetailsdto;

public class StudentMapper {

	public static Student mapStudentDtoToStudentEntity(StudentDetailsdto dto,Student entity) {
		entity.setPrn(dto.getPrn());
		entity.setStudentName(dto.getStudentName());
		System.out.println("In Mapper method of Student");
		System.out.println(entity);
		return entity;
	}
}
