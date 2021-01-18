package com.app.restcontroller;

import java.util.ArrayList;
import java.util.Optional;

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
import com.app.dto.Choicesdto;
import com.app.dto.QuestionRequestdto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.mapper.QuestionMapper;
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
		System.out.println(questionDto);
		Paper detachedPaper = null;
		Optional<Paper> fetchedPaper = paperService.findById(questionDto.getPaperId());
		System.out.println("Fetched paper");
		System.out.println(fetchedPaper.get());
		if (fetchedPaper.isPresent()) {
			detachedPaper = fetchedPaper.get();
			transientQuestion = QuestionMapper.mapQuestionDtoToQuestionEntity(questionDto, transientQuestion);
			transientQuestion.setPaper(detachedPaper);
			Questions createdQuestion = questionService.createQuestion(transientQuestion);

			if (createdQuestion != null) {
				ArrayList<Choicesdto> choices = questionDto.getChoices();
				for (Choicesdto choice : choices) {
					transientChoice = ChoicesMapper.mapChoiceDtoToQuestionsChoicesEntity(choice, transientChoice);
					transientChoice.setQuestion(createdQuestion);
					System.out.println("Tramsient Choice after question added");
					System.out.println(choice);
					choiceService.insertChoice(transientChoice);
				}
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			throw new RuntimeException("Error accords while creating question");
		} else {
			throw new NoSuchElementException("No record of paper found for given papper id");
		}
	}

}
