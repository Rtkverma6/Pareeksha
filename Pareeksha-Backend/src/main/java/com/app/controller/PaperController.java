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

import com.app.dto.PaperRequestDTO;
import com.app.pojos.Paper;
import com.app.pojos.PaperSetter;
import com.app.service.IPaperService;
import com.app.service.IPaperSetterService;

@RestController
@CrossOrigin
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	IPaperService paperService;
	@Autowired
	IPaperSetterService paperSetterService;

	@PostMapping("/create")
	public ResponseEntity<?> generatePaper(@RequestBody @Valid PaperRequestDTO paper, Paper transientPaper) {
		System.out.println("In createPaper()");
		System.out.println(paper);

		PaperSetter paperSetter= null;
		//Getting PapperSetter Object by paperSetter Id 
		Optional<PaperSetter> detachedPaperSetter = paperSetterService.findById(paper.getPaperSetterId());
		if (detachedPaperSetter.isPresent()) {
			 paperSetter=detachedPaperSetter.get();
			 transientPaper.setPaperSetter(paperSetter);
				transientPaper.setPaperName(paper.getPaperName());
				transientPaper.setPaperSubject(paper.getPaperSubject());
				transientPaper.setPaperPassword(paper.getPaperPassword());
				transientPaper.setStartDate(paper.getStartDate());
				transientPaper.setEndDate(paper.getEndDate());
				transientPaper.setDuration(paper.getDuration());

				try {
					Paper createPaper = paperService.createPaper(transientPaper);
					return new ResponseEntity<>(createPaper, HttpStatus.CREATED);
				} catch (RuntimeException e) {
					System.out.println("Error in creating paper" + e);
					return new ResponseEntity<>("Failed to create new Paper", HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}else {
			//throw new ResponseEntity<>("No content found ", HttpStatus.NO_CONTENT);
			return new ResponseEntity<>("No Such data found", HttpStatus.NO_CONTENT);
		}
		
		

	}
}
