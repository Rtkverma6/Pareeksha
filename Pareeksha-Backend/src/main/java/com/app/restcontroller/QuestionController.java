package com.app.restcontroller;

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
import com.app.dto.QuestionRequestdto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.QuestionMapper;
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
	public ResponseEntity<?> createQuestion(@RequestBody @Valid QuestionRequestdto questionDto,Questions transientQuestion){
		System.out.println(" In createQuestion()");
		Paper detachedPaper = null;
		Optional<Paper> fetchedPaper = paperService.findById(questionDto.getPaperId());
		
		if (fetchedPaper.isPresent()) {
			detachedPaper=fetchedPaper.get();
			transientQuestion=QuestionMapper.mapQuestionDtoToQuestionEntity(questionDto, transientQuestion);
			transientQuestion.setPaper(detachedPaper);
			Questions createdQuestion = questionService.createQuestion(transientQuestion);
			if (createdQuestion != null) {
				return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
			}
			throw new RuntimeException("Error accords while creating question");
		}else {
			throw new NoSuchElementException("No Such data found for given Paper Id");
		}
	}
	
}