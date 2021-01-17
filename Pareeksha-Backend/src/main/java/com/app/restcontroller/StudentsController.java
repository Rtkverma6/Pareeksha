package com.app.restcontroller;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.entity.Paper;
import com.app.dao.entity.Student;
import com.app.dto.StudentDetailsdto;
import com.app.mapper.StudentMapper;
import com.app.service.IPaperService;
import com.app.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentsController {

	@Autowired
	IStudentService studentService;
	@Autowired
	IPaperService paperService;
	
	@PostMapping("/login")
	public ResponseEntity<?> saveStudentDetails (@RequestBody @Valid StudentDetailsdto dto , Student transientStudent){
		System.out.println("In saveStudentDetails()");
		
		Optional<Paper> detachedPaper = paperService.findById(dto.getPaperId());
		if (detachedPaper.isPresent()) {
			//Mapping Students dto to student entity
			transientStudent = StudentMapper.mapStudentDtoToStudentEntity(dto, transientStudent);
			Paper paper = detachedPaper.get();
			transientStudent.setPaper(paper);
			Student createdStudentRecord = studentService.createStudentRecord(transientStudent);
			if (createdStudentRecord != null) {
				return new ResponseEntity<>(createdStudentRecord, HttpStatus.CREATED);
			}
				throw new RuntimeException("Failed to create Student Record please try again");
		}
		else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}
	}
}
