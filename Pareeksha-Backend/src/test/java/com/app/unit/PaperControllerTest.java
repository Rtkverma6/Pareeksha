package com.app.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.app.pojos.Paper;
import com.app.service.IPaperService;
import com.app.service.IPaperSetterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class PaperControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@MockBean
	private IPaperService service;
	@MockBean
	private IPaperSetterService pservice;
	@Autowired
	private ObjectMapper mapper;// Jackson supplied class for JSON processing.
	
	
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders
//				.webAppContextSetup(context)
//				.apply()
//				.build();
//	}
	
	
	@Test
	public void testGeneratePaper() throws Exception {

		Paper p = new Paper();
		p.setPaperName("Test");
		p.setPaperSubject("Test");
		p.setReviewed(false);
		p.setPaperPassword("132456");
		p.setDuration(LocalTime.parse("01:00:00"));
		p.setStartDate(LocalDateTime.parse("2021-01-15T12:59:11.332"));
		p.setEndDate(LocalDateTime.parse("2021-01-16T12:59:11.332"));
		p.setPaperId(2L);
		
		String json = mapper.writeValueAsString(p);
		when(service.createPaper(p)).thenReturn(p);
		mockMvc.perform(post("/paper/create").content(json).contentType(MediaType.APPLICATION_JSON)).
		andExpect(status().isCreated());
		
	}

}
