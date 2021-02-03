package com.app.restcontroller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dao.entity.Student;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.PaperLogindto;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;
import com.app.dto.QuestionResponsedto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.ChoicesMapper;
import com.app.mapper.PaperMapper;
import com.app.mapper.QuestionMapper;
import com.app.service.IChoicesService;
import com.app.service.IPaperService;
import com.app.service.IQuestionsService;
import com.app.service.IStudentService;
import com.app.service.PaperPdfExporter;
import com.app.service.ResultPdfExporter;

@RestController
@CrossOrigin
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	IPaperService paperService;
	@Autowired
	IStudentService studentService;
	@Autowired
	IQuestionsService questionsService;
	@Autowired
	IChoicesService choiceService;
	@Autowired
	IStudentService studentsService;

	@PostMapping("/create")
	public ResponseEntity<?> generatePaper(@RequestBody @Valid PaperRequestdto paper, Paper transientPaper) {
		System.out.println("In createPaper()");
		System.out.println(paper);
		return new ResponseEntity<>(paperService.createPaper(paper, transientPaper), HttpStatus.CREATED);
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
		return new ResponseEntity<>(paperService.fetchPaper(paperId, paper, questions, fetchChoices, paperResponse),
				HttpStatus.OK);
	}

	@GetMapping("/to-review/{paperId}")
	public ResponseEntity<?> fetchToReviewPaper(@PathVariable Long paperId, Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse) {
		System.out.println("In fetchPaper()");
		return new ResponseEntity<>(
				paperService.fetchUnReviewedPaper(paperId, paper, questions, fetchChoices, paperResponse),
				HttpStatus.OK);
	}

	@GetMapping("/details/{paperSetterId}")
	public ResponseEntity<?> fetchAllPaperDetails(@PathVariable Long paperSetterId,
			ArrayList<PaperResponsedto> papers) {
		System.out.println("In fetchAllPaperDetails()");
		ArrayList<Paper> detachedPapers = paperService.findByPaperSetterId(paperSetterId);
		detachedPapers.forEach(d -> papers.add(PaperMapper.mapPaperEntityToPaperDto(d, new PaperResponsedto())));
		System.out.println(papers);
		return new ResponseEntity<>(papers, HttpStatus.OK);
	}

	@GetMapping("/published-paper/{paperSetterId}")
	public ResponseEntity<?> fetchPublishedPaper(@PathVariable Long paperSetterId, ArrayList<Paper> papers) {
		System.out.println("In fetchPublishedPaper()");
		papers = paperService.fetchPublishedPapers(paperSetterId);
		return new ResponseEntity<>(papers, HttpStatus.OK);
	}

	@GetMapping("/fetch-results/{paperId}")
	public ResponseEntity<?> fetchResults(@PathVariable Long paperId, ArrayList<Student> students) {
		System.out.println("In fetchResults()");
		return new ResponseEntity<>(studentService.fetchAllStudentsResults(paperId, students), HttpStatus.OK);
	}

	@GetMapping("/review/{paperId}")
	public ResponseEntity<?> updatePaperStatus(@PathVariable Long paperId) {
		System.out.println("--------------------------------------------------");
		System.out.println("In updatePaperStatus()");
		return new ResponseEntity<>(paperService.updatePaperStatus(paperId), HttpStatus.OK);

	}

	@GetMapping("/download/{paperId}")
	public void downloadPaper(@PathVariable Long paperId, HttpServletResponse response) {
		System.out.println("In downloadPaper()");
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=paper_" + paperId + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		Optional<Paper> fetchedPaper = paperService.findById(paperId);
		if (fetchedPaper.isPresent()) {
			Paper paper = fetchedPaper.get();
			if (!paper.isReviewed()) {
				throw new RuntimeException("Paper Not reviewd yet please review paper first..");
			}
			ArrayList<Questions> questions = questionsService.fetchAllQuestions(paper.getPaperId());
			ArrayList<QuestionResponsedto> questionsDto = new ArrayList<>();
			for (Questions question : questions) {
				ArrayList<QuestionsChoices> fetchChoices = choiceService.fetchChoices(question.getQuestionId());
				ArrayList<ChoiceResponsedto> choices = new ArrayList<>();
				QuestionResponsedto questionDto = new QuestionResponsedto();
				choices = ChoicesMapper.mapChoiceEntityToChoiceResponseDto(fetchChoices, choices);
				questionDto = QuestionMapper.mapQuestionEntityToQuestionResponseDto(question, questionDto, choices);
				questionsDto.add(questionDto);
			}
			PaperPdfExporter exporter = new PaperPdfExporter();
			exporter.setPaper(paper);
			exporter.setQuestion(questionsDto);
			try {
				exporter.export(response);
			} catch (Exception e) {
				new RuntimeException("Failed to create PDF");
			}
		} else {
			throw new NoSuchElementException("No paper records found for given paper Id");
		}
	}

	@GetMapping("/download-result/{paperId}")
	public void downloadPaperResults(@PathVariable Long paperId, HttpServletResponse response,ArrayList<Student> students) {
		System.out.println("In downloadPaperResults");
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=paperResults_" + paperId + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		
		students=studentService.fetchAllStudentsResults(paperId, students);
		Optional<Paper> fetchedPaper = paperService.findById(paperId);
		if (fetchedPaper.isPresent()) {
			Paper paper = fetchedPaper.get();
			ResultPdfExporter exporter = new ResultPdfExporter(students, paper);
			try {
				exporter.export(response);
			} catch (Exception e) {
				new RuntimeException("Failed to create PDF");
			}
		}else {
			throw new NoSuchElementException("No paper records found for given paper Id");
		}
		
	}
}
