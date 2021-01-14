package com.app.controller;

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

import com.app.dto.QuestionRequestDTO;
import com.app.pojos.Paper;
import com.app.pojos.Questions;
import com.app.service.IPaperService;
import com.app.service.IQuestionsService;

@RestController
@CrossOrigin
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	IPaperService paperService;
	@Autowired
	IQuestionsService questionService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createQuestion(@RequestBody @Valid QuestionRequestDTO questionDto,Questions transientQuestion){
		System.out.println(" In createQuestion()");
		Paper detachedPaper = null;
		Optional<Paper> fetchedPaper = paperService.findById(questionDto.getPaperId());
		
		if (fetchedPaper.isPresent()) {
			detachedPaper=fetchedPaper.get();
			transientQuestion.setPaper(detachedPaper);
			transientQuestion.setPoints(questionDto.getPoints());
			transientQuestion.setQuestion(questionDto.getQuestion());
			Questions createdQuestion = questionService.createQuestion(transientQuestion);
			if (createdQuestion != null) {
				return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("Failed to create question", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<>("No such Paper found in database", HttpStatus.NO_CONTENT);
		}
	}
}
