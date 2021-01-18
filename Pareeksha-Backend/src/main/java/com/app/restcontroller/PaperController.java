package com.app.restcontroller;

import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dao.entity.Paper;
import com.app.dao.entity.PaperSetter;
import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.PaperLogindto;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;
import com.app.dto.QuestionResponsedto;
import com.app.exception.IllegalArgumentException;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.mapper.PaperMapper;
import com.app.mapper.QuestionMapper;
import com.app.service.IChoicesService;
import com.app.service.IPaperService;
import com.app.service.IPaperSetterService;
import com.app.service.IQuestionsService;

@RestController
@CrossOrigin
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	IPaperService paperService;
	@Autowired
	IPaperSetterService paperSetterService;
	@Autowired
	IQuestionsService questionsService;
	@Autowired
	IChoicesService choiceService;

	@PostMapping("/create")
	public ResponseEntity<?> generatePaper(@RequestBody @Valid PaperRequestdto paper, Paper transientPaper) {
		System.out.println("In createPaper()");
		System.out.println(paper);

		PaperSetter paperSetter = null;
		// Getting PapperSetter Object by paperSetter Id
		Optional<PaperSetter> detachedPaperSetter = paperSetterService.findById(paper.getPaperSetterId());
		if (detachedPaperSetter.isPresent()) {
			paperSetter = detachedPaperSetter.get();
			transientPaper = PaperMapper.mapPaperDtoToPaperEntity(paper, transientPaper);
			transientPaper.setPaperSetter(paperSetter);
			System.out.println(transientPaper);
			System.out.println(transientPaper.getPaperSetter());

			try {
				Paper createPaper = paperService.createPaper(transientPaper);
				if (createPaper != null) {
					return new ResponseEntity<>(createPaper, HttpStatus.CREATED);
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

	@PostMapping("/login")
	public ResponseEntity<?> verifyPaperDetails(@RequestBody @Valid PaperLogindto dto, Paper paper) {
		System.out.println("In verifyPaperDetails()");
		paper = paperService.findByPaperIdAndPaperPassword(dto.getPaperId(), dto.getPaperPassword());

		if (paper != null) {
			return new ResponseEntity<>(paper, HttpStatus.ACCEPTED);
		} else {
			throw new NoSuchElementException("Paper details provided are not valid");
		}
	}

	@GetMapping("/fetch/{paperId}")
	public ResponseEntity<?> fetchPaper(@PathVariable Long paperId, Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse) {
		System.out.println("In fetchPaper()");
		System.out.println(paperId);
		Optional<Paper> fetchedPaper = paperService.findById(paperId);
		if (fetchedPaper.isPresent()) {
			paper = fetchedPaper.get();
			paperResponse.setPaperName(paper.getPaperName());
			paperResponse.setPaperSubject(paper.getPaperSubject());
			System.out.println("------------------------------------------");
			System.out.println(paper);
			System.out.println("------------------------------------------");
			questions = questionsService.fetchAllQuestions(paperId);
			System.out.println(questions);
			System.out.println("------------------------------------------");

			for (Questions question : questions) {
				fetchChoices = choiceService.fetchChoices(question.getQuestionId());
				ArrayList<ChoiceResponsedto> choices = new ArrayList<>();
				QuestionResponsedto questionDto = new QuestionResponsedto();
				choices = ChoicesMapper.mapChoiceEntityToChoiceResponseDto(fetchChoices, choices);
				questionDto = QuestionMapper.mapQuestionEntityToQuestionResponseDto(question, questionDto, choices);
				paperResponse.addQuestion(questionDto);
			}
			System.out.println("------------------------------------------");
			System.out.println(paperResponse);
			System.out.println("------------------------------------------");
			return new ResponseEntity<>(paperResponse, HttpStatus.OK);
		} else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}

	}
}