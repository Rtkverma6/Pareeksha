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

import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dto.Choicesdto;
import com.app.dto.Responsedto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.service.IChoicesService;
import com.app.service.IQuestionsService;

@RestController
@CrossOrigin
@RequestMapping("/choice")
public class ChoicesController {

	@Autowired
	IQuestionsService questionService;
	@Autowired
	IChoicesService choiceService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insertChoice(@RequestBody @Valid Choicesdto choiceDto,QuestionsChoices transientChoice,Responsedto res){
		System.out.println("In insertChoice()");
		
		Questions detachedQuestion = null;
		Optional<Questions> fetchedQuestion = questionService.fetchQuestion(choiceDto.getQuestionId());
		if (fetchedQuestion.isPresent()) {
			detachedQuestion=fetchedQuestion.get();
			transientChoice = ChoicesMapper.mapQuestionDtoToQuestionEntity(choiceDto, transientChoice);
			transientChoice.setQuestion(detachedQuestion);
			QuestionsChoices insertedChoice = choiceService.insertChoice(transientChoice);
			if (insertedChoice != null) {
				return new ResponseEntity<>(insertedChoice, HttpStatus.CREATED);
			}
			throw new RuntimeException("Error accords while saving paper");
		}
		else 
			throw new NoSuchElementException("No Such data found for given Question Id");
	}
}
