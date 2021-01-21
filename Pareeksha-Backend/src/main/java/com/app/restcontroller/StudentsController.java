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
import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dao.entity.Student;
import com.app.dto.PaperResultdto;
import com.app.dto.StudentDetailsdto;
import com.app.mapper.StudentMapper;
import com.app.service.IChoicesService;
import com.app.service.IPaperService;
import com.app.service.IQuestionsService;
import com.app.service.IStudentResponseService;
import com.app.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/student")
public class StudentsController {

	@Autowired
	IStudentService studentService;
	@Autowired
	IPaperService paperService;
	@Autowired
	IChoicesService choiceService;
	@Autowired
	IQuestionsService questionService;
	@Autowired
	IStudentResponseService responseService;

	@PostMapping("/login")
	public ResponseEntity<?> saveStudentDetails(@RequestBody @Valid StudentDetailsdto dto, Student transientStudent) {
		System.out.println("In saveStudentDetails()");
		System.out.println("---------------------**********************************-----------------------");
		System.out.println(dto);
		System.out.println("---------------------**********************************-------------------------");
		Optional<Paper> detachedPaper = paperService.findById(dto.getPaperId());
		if (detachedPaper.isPresent()) {
			// Mapping Students dto to student entity
			transientStudent = StudentMapper.mapStudentDtoToStudentEntity(dto, transientStudent);
			Paper paper = detachedPaper.get();
			transientStudent.setPaper(paper);
			Student createdStudentRecord = studentService.createStudentRecord(transientStudent);
			if (createdStudentRecord != null) {
				return new ResponseEntity<>(createdStudentRecord, HttpStatus.CREATED);
			} else {
				throw new RuntimeException("Failed to create Student Record please try again");
			}
		} else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}
	}

	@PostMapping("/result")
	public ResponseEntity<?> saveStudentsPaperResponse(@RequestBody PaperResultdto resultDto, Questions question,
			QuestionsChoices choice) {
		System.out.println("In saveStudentsPaperResponse()");
		System.out.println("Printing Result Dto \n=================================================");
		System.out.println(resultDto);
		Optional<Student> fetchedStudent = studentService.findByStudentId(resultDto.getStudentId());
		if (fetchedStudent.isPresent()) {
			Student student = fetchedStudent.get();
			student.setSubmittedOn(resultDto.getSubmittedOn());
			student.setMarksObtained(resultDto.getMarksObtained());
			student.setSubmittedOn(resultDto.getSubmittedOn());
			studentService.createStudentRecord(student);
		} else {
			throw new NoSuchElementException("Error in saving result of student");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
