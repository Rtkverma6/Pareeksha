package com.app.service;

import java.util.Optional;

import com.app.dao.entity.Student;

public interface IStudentService {

	public Student createStudentRecord(Student transientStudent);
	
	public Optional<Student> findByStudentId(Long id) ;
}
