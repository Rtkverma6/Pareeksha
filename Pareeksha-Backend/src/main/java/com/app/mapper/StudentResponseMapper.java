package com.app.mapper;

import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dao.entity.Student;
import com.app.dao.entity.StudentResponses;

public class StudentResponseMapper {

	public static StudentResponses MapPaperResultToStudentResponseEntity(StudentResponses entity,Questions question,QuestionsChoices choice,Student student) {
		entity.setQuestion(question);
		entity.setSelectedChoice(choice);
		entity.setStudent(student);
		return entity;
	}
}
