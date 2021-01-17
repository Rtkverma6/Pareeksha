package com.app.service;

import com.app.dao.entity.Student;

public interface IStudentService {

	public Student createStudentRecord(Student transientStudent);
}
