package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.StudentResponseRepo;
import com.app.dao.entity.StudentResponses;

@Service
@Transactional
public class StudentResponseServiceImpl implements IStudentResponseService{

	@Autowired
	StudentResponseRepo repo;
	
	@Override
	public void saveResponses(StudentResponses transientStudentResponses) {
		repo.save(transientStudentResponses);
	}

}
