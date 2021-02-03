package com.app.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.app.dao.entity.Paper;
import com.app.dao.entity.PaperSetter;
import com.app.service.IPaperService;
import com.app.service.IPaperSetterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class PaperControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IPaperService service;
	@MockBean
	private IPaperSetterService pservice;
	@Autowired
	private ObjectMapper mapper;// Jackson supplied class for JSON processing.
		
	@Test
	public void testGeneratePaper() throws Exception {

		Paper p = new Paper();
		p.setPaperName("Test");
		p.setPaperSubject("Test");
		p.setReviewed(false);
		p.setPaperId(1L);
		p.setPaperPassword("132456");
		p.setDuration(LocalTime.parse("01:00:00"));
		p.setStartDate(LocalDateTime.parse("2021-01-15T12:59:11.332"));
		p.setEndDate(LocalDateTime.parse("2021-01-16T12:59:11.332"));
		p.setPaperId(2L);
//		 new PaperSetter(null, "Sumit", "sumit@gmail", LocalDate.parse("1999-10-10"), "10234569");
//			t.setPaperSetterId(2L);
		Optional<PaperSetter> t = null;
		
		Mockito.when(pservice.findById(2L)).thenReturn(t);
		
		String json = mapper.writeValueAsString(p);
		//Mockito.when((service.createPaper(Mockito.any(Paper.class)))).thenReturn(p);
		mockMvc.perform(post("/paper/create").content(json).contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isCreated());
		
	}

}
