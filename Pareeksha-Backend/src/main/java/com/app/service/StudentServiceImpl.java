package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.StudentRepo;
import com.app.dao.entity.Student;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepo repo;
	
	@Override
	public Student createStudentRecord(Student transientStudent) {
		return repo.save(transientStudent);
	}

	@Override
	public Optional<Student> findByStudentId(Long id) {
		return repo.findById(id);
	}

}
