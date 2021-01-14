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

import com.app.dto.ChoicesDTO;
import com.app.dto.ResponseDTO;
import com.app.pojos.Questions;
import com.app.pojos.QuestionsChoices;
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
	public ResponseEntity<?> insertChoice(@RequestBody @Valid ChoicesDTO choiceDto,QuestionsChoices transientChoice,ResponseDTO res){
		System.out.println("In insertChoice()");
		
		Questions detachedQuestion = null;
		Optional<Questions> fetchedQuestion = questionService.fetchQuestion(choiceDto.getQuestionId());
		if (fetchedQuestion.isPresent()) {
			detachedQuestion=fetchedQuestion.get();
			
			transientChoice.setQuestion(detachedQuestion);
			transientChoice.setChoice(choiceDto.getChoice());
			transientChoice.setCorrect(choiceDto.getIsCorrect());
			QuestionsChoices insertedChoice = choiceService.insertChoice(transientChoice);
			if (insertedChoice != null) {
				return new ResponseEntity<>(insertedChoice, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<>("Failed to insert new Choice in Question", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			return new ResponseEntity<>("Failed To find question with given Id", HttpStatus.NO_CONTENT);
		}
		
	}
}
