package com.app.restcontroller;

import java.util.ArrayList;

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

import com.app.dao.repo.entity.Paper;
import com.app.dao.repo.entity.Questions;
import com.app.dao.repo.entity.QuestionsChoices;
import com.app.dao.repo.entity.Student;
import com.app.dto.PaperLogindto;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;
import com.app.exception.NoSuchElementException;
import com.app.mapper.PaperMapper;
import com.app.service.IPaperService;
import com.app.service.IStudentService;

@RestController
@CrossOrigin
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	IPaperService paperService;
	@Autowired IStudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<?> generatePaper(@RequestBody @Valid PaperRequestdto paper, Paper transientPaper) {
		System.out.println("In createPaper()");
		System.out.println(paper);
		return new ResponseEntity<> (paperService.createPaper(paper, transientPaper),HttpStatus.CREATED);
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
		return new ResponseEntity<> (paperService.fetchPaper(paperId, paper, questions, fetchChoices, paperResponse),HttpStatus.OK);
	}
	
	@GetMapping("/to-review/{paperId}")
	public ResponseEntity<?> fetchToReviewPaper(@PathVariable Long paperId, Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse) {
		System.out.println("In fetchPaper()");
		return new ResponseEntity<> (paperService.fetchUnReviewedPaper(paperId, paper, questions, fetchChoices, paperResponse),HttpStatus.OK);
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
	public ResponseEntity<?> fetchPublishedPaper(@PathVariable Long paperSetterId,ArrayList<Paper> papers){
		System.out.println("In fetchPublishedPaper()");
		papers=paperService.fetchPublishedPapers(paperSetterId);
		return new ResponseEntity<>(papers, HttpStatus.OK);
	}

	@GetMapping("/fetch-results/{paperId}")
	public ResponseEntity<?> fetchResults(@PathVariable Long paperId,ArrayList<Student> students){
		System.out.println("In fetchResults()");
		return new ResponseEntity<> (studentService.fetchAllStudentsResults(paperId, students),HttpStatus.OK);
	}
	
	
	@GetMapping("/review/{paperId}")
	public ResponseEntity<?> updatePaperStatus(@PathVariable Long paperId) {
		System.out.println("--------------------------------------------------");
		System.out.println("In updatePaperStatus()");
		return new ResponseEntity<> (paperService.updatePaperStatus(paperId),HttpStatus.OK);

	}
}
