package com.app.restcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.repo.entity.Questions;
import com.app.dao.repo.entity.QuestionsChoices;
import com.app.dao.repo.entity.Student;
import com.app.dto.PaperResultdto;
import com.app.dto.StudentDetailsdto;
import com.app.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentsController {

	@Autowired
	IStudentService studentService;

	@PostMapping("/login")
	public ResponseEntity<?> saveStudentDetails(@RequestBody @Valid StudentDetailsdto dto, Student transientStudent) {
		System.out.println("In saveStudentDetails()");
		return new ResponseEntity<> (studentService.createStudentRecord(dto, transientStudent),HttpStatus.OK);
	}

	@PostMapping("/result")
	public ResponseEntity<?> saveStudentsPaperResponse(@RequestBody PaperResultdto resultDto, Questions question,
			QuestionsChoices choice) {
		System.out.println("In saveStudentsPaperResponse()");
		return new ResponseEntity<> (studentService.saveStudentsPaperResponse(resultDto, question, choice),HttpStatus.OK);
	}

}
