package com.app.restcontroller;

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
import com.app.dto.QuestionRequestdto;
import com.app.service.IChoicesService;
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
	@Autowired
	IChoicesService choiceService;

	@PostMapping("/create")
	public ResponseEntity<?> createQuestion(@RequestBody QuestionRequestdto questionDto, Questions transientQuestion,
			QuestionsChoices transientChoice){
		System.out.println(questionDto);
		System.out.println(" In createQuestion()");
		return new ResponseEntity<> (questionService.createQuestion(transientQuestion, questionDto, transientChoice),HttpStatus.OK);
		
	}

}
