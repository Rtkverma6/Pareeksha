package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.entity.Paper;
import com.app.dao.entity.PaperSetter;
import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dao.repo.PaperRepo;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;
import com.app.dto.QuestionResponsedto;
import com.app.exception.IllegalArgumentException;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.mapper.PaperMapper;
import com.app.mapper.QuestionMapper;

@Service
@Transactional
public class PaperServiceImpl implements IPaperService {

	@Autowired
	PaperRepo repo;
	@Autowired
	IPaperSetterService paperSetterService;
	@Autowired
	IQuestionsService questionsService;
	@Autowired
	IChoicesService choiceService;

	@Override
	public Paper createPaper(PaperRequestdto paper,Paper transientPaper ) {
		
		PaperSetter paperSetter = null;
		Optional<PaperSetter> detachedPaperSetter = paperSetterService.findById(paper.getPaperSetterId());
		if (detachedPaperSetter.isPresent()) {
			paperSetter = detachedPaperSetter.get();
			transientPaper = PaperMapper.mapPaperDtoToPaperEntity(paper, transientPaper);
			transientPaper.setPaperSetter(paperSetter);
			System.out.println(transientPaper);
			System.out.println(transientPaper.getPaperSetter());

			try {
				Paper createPaper = repo.save(transientPaper);
				if (createPaper != null) {
					return createPaper;
				} else {
					throw new IllegalArgumentException("Failed to create Paper");
				}
			} catch (RuntimeException e) {
				System.out.println("Error in creating paper" + e);
				throw new RuntimeException("Error accords while saving paper");
			}
		} else {
			throw new NoSuchElementException("No Such data found for given PaperSetter Id");
		}
	}
	
	@Override
	public PaperResponsedto fetchPaper(Long paperId,Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse) {
		
		Optional<Paper> fetchedPaper = repo.findById(paperId);
		if (fetchedPaper.isPresent()) {
			paper = fetchedPaper.get();
			String paperStatus = isPaperActive(paper);
			if(paperStatus != "This paper is active" ) {
				throw new RuntimeException("Paper is not activetd yet please try later");
			}
			paperResponse = PaperMapper.mapPaperEntityToPaperDto(paper, paperResponse);
			questions = questionsService.fetchAllQuestions(paperId);

			for (Questions question : questions) {
				fetchChoices = choiceService.fetchChoices(question.getQuestionId());
				ArrayList<ChoiceResponsedto> choices = new ArrayList<>();
				QuestionResponsedto questionDto = new QuestionResponsedto();
				choices = ChoicesMapper.mapChoiceEntityToChoiceResponseDto(fetchChoices, choices);
				questionDto = QuestionMapper.mapQuestionEntityToQuestionResponseDto(question, questionDto, choices);
				paperResponse.addQuestion(questionDto);
			}
			System.out.println("___________________________________________");
			System.out.println(paperResponse);
			System.err.println("____________________________________________");
			return paperResponse;
		} else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}
	}
	
	@Override
	public PaperResponsedto fetchUnReviewedPaper(Long paperId,Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse) {
		
		Optional<Paper> fetchedPaper = repo.findById(paperId);
		if (fetchedPaper.isPresent()) {
			paper = fetchedPaper.get();
			
			paperResponse = PaperMapper.mapPaperEntityToPaperDto(paper, paperResponse);
			questions = questionsService.fetchAllQuestions(paperId);

			for (Questions question : questions) {
				fetchChoices = choiceService.fetchChoices(question.getQuestionId());
				ArrayList<ChoiceResponsedto> choices = new ArrayList<>();
				QuestionResponsedto questionDto = new QuestionResponsedto();
				choices = ChoicesMapper.mapChoiceEntityToChoiceResponseDto(fetchChoices, choices);
				questionDto = QuestionMapper.mapQuestionEntityToQuestionResponseDto(question, questionDto, choices);
				paperResponse.addQuestion(questionDto);
			}
			System.out.println("___________________________________________");
			System.out.println(paperResponse);
			System.err.println("____________________________________________");
			return paperResponse;
		} else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}
	}

	@Override
	public Optional<Paper> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public Paper findByPaperIdAndPaperPassword(Long id, String password) {
		Paper validatedPaper = repo.findByPaperIdAndPaperPassword(id, password);
		return validatedPaper;
	}

	public String isPaperActive(Paper detachedPaper) {
		if (detachedPaper.isReviewed()) {
			if (detachedPaper.getStartDate().isBefore(LocalDateTime.now())) {
				if (detachedPaper.getEndDate().isAfter(LocalDateTime.now())) {
					return "This paper is active";
				} else {
					return "Requested Paper has Expired";
				}
			} else {
				return "Requested Paper is not activated yet";
			}
		} else {
			return "Requested Paper is not reviewed yet";
		}
	}

	@Override
	public String updatePaperStatus(Long paperId) {
		System.out.println("---------------------------------------------------------");
		System.out.println("In save method");
		Optional<Paper> fetchedPaper = repo.findById(paperId);
		if (fetchedPaper.isPresent()) {
			Paper detachedPaper = fetchedPaper.get();
			detachedPaper.setReviewed(true);
			repo.save(detachedPaper);
			return "Paper status Updated";
		}else {
			throw new NoSuchElementException("No paper Present for given paperId");
		}
	}

	@Override
	public ArrayList<Paper> findByPaperSetterId(Long paperSetterId) {
		return repo.findByPaperSettterId(paperSetterId);
	}

	@Override
	public ArrayList<Paper> fetchPublishedPapers(Long paperSetterId) {
		 ArrayList<Paper> fetchedPapers = repo.findByPaperSettterId
				 (paperSetterId);
		 ArrayList<Paper> activePapers = new ArrayList<>();
		for (Paper paper : fetchedPapers) {
			if (isPaperActive(paper) == "This paper is active") {
				activePapers.add(paper);
			}
		}
		 return activePapers;
	}
}
