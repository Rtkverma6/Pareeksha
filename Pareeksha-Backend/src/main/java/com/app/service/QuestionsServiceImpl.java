package com.app.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.repo.QuestionsRepo;
import com.app.dao.repo.entity.Paper;
import com.app.dao.repo.entity.Questions;
import com.app.dao.repo.entity.QuestionsChoices;
import com.app.dto.Choicesdto;
import com.app.dto.QuestionRequestdto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.mapper.QuestionMapper;

@Service
public class QuestionsServiceImpl implements IQuestionsService {

	@Autowired
	QuestionsRepo repo;
	@Autowired
	IPaperService paperService;
	@Autowired
	IChoicesService choiceService;

	@Override
	public String createQuestion(Questions transientQuestion, QuestionRequestdto questionDto,
			QuestionsChoices transientChoice) {
		Paper detachedPaper = null;
		Optional<Paper> fetchedPaper = paperService.findById(questionDto.getPaperId());
		System.out.println("Fetched paper");
		System.out.println(fetchedPaper.get());
		if (fetchedPaper.isPresent()) {
			detachedPaper = fetchedPaper.get();
			transientQuestion = QuestionMapper.mapQuestionDtoToQuestionEntity(questionDto, transientQuestion);
			transientQuestion.setPaper(detachedPaper);
			Questions createdQuestion = repo.save(transientQuestion);

			if (createdQuestion != null) {
				ArrayList<Choicesdto> choices = questionDto.getChoices();
				for (Choicesdto choice : choices) {
					transientChoice = ChoicesMapper.mapChoiceDtoToQuestionsChoicesEntity(choice, transientChoice);
					transientChoice.setQuestion(createdQuestion);
					System.out.println("Tramsient Choice after question added");
					System.out.println(choice);
					choiceService.insertChoice(transientChoice);
				}
				return "Question Inserted into paper";
			}
			throw new RuntimeException("Error accords while creating question");
		} else {
			throw new NoSuchElementException("No record of paper found for given papper id");
		}
	}

	@Override
	public Optional<Questions> fetchQuestion(Long id) {
		return repo.findById(id);
	}

	@Override
	public ArrayList<Questions> fetchAllQuestions(long paperId) {
		ArrayList<Questions> fetchedAllQuestions = repo.fetchAllQuestions(paperId);
		System.out.println(fetchedAllQuestions);
		return fetchedAllQuestions;
	}

}
