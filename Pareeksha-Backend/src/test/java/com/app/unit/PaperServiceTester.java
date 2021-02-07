package com.app.unit;


import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.dao.entity.Paper;
import com.app.dao.repo.PaperRepo;
import com.app.dto.PaperRequestdto;
import com.app.mapper.PaperMapper;
import com.app.service.IChoicesService;
import com.app.service.IPaperService;
import com.app.service.IPaperSetterService;
import com.app.service.IQuestionsService;

@SpringBootTest
public class PaperServiceTester {

	@MockBean
	PaperRepo repo;
	@Mock
	IPaperSetterService paperSetterService;
	@Mock
	IQuestionsService questionsService;
	@Mock
	IChoicesService choiceService;
	@Mock
	IPaperService service;
	
	@Test
	public void testCreatePaper() {
		PaperRequestdto paper = new PaperRequestdto() ;
		paper.setPaperName("test");
		paper.setPaperPassword("123456");
		paper.setStartDate(LocalDateTime.parse("2015-08-03T10:11:30"));
		paper.setEndDate(LocalDateTime.parse("2015-08-04T10:11:30"));
		paper.setDifficultyLevel("EASY");
		paper.setDuration(LocalTime.parse("00:30"));
		paper.setPaperSubject("JAVA");
		paper.setReviewed(false);
		paper.setTotalMarks(5);
		paper.setTotalQuestions(5);
		paper.setPaperSetterId(1L);
		
		Paper transientPaper = new Paper();
		PaperMapper.mapPaperDtoToPaperEntity(paper, transientPaper);
		Mockito.when(service.createPaper(paper, transientPaper)).thenReturn(transientPaper);
		Paper createdPaper = service.createPaper(paper, transientPaper);
		assertEquals("JAVA",createdPaper.getPaperSubject());
	}
	
	
}
