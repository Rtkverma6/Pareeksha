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

import com.app.cust_excs.IllegalArgumentException;
import com.app.cust_excs.NoSuchElementException;
import com.app.dto.PaperRequestDTO;
import com.app.mapper.PaperMapper;
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
			transientPaper = PaperMapper.mapPaperDtoToPaperEntity(paper, transientPaper);
			 transientPaper.setPaperSetter(paperSetter);
			 System.out.println(transientPaper);
			 System.out.println(transientPaper.getPaperSetter());

				try {
					Paper createPaper = paperService.createPaper(transientPaper);
					if (createPaper != null) {
						return new ResponseEntity<>(createPaper, HttpStatus.CREATED);
					}else {
						throw new IllegalArgumentException("Failed to create Paper");
					}
				} catch (RuntimeException e) {
					System.out.println("Error in creating paper" + e);
					throw new RuntimeException("Error accords while saving paper");
			}
		}else {
			throw new NoSuchElementException("No Such data found for given PaperSetter Id");
		}
	}
}
