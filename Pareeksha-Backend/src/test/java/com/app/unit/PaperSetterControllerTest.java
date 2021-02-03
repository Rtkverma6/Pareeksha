package com.app.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.app.dao.entity.PaperSetter;
import com.app.service.IPaperSetterService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class PaperSetterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IPaperSetterService service;

//	@Test
//	void smokeTest() {
//		assertNotNull(controller);
//	}

	@Autowired
	private ObjectMapper mapper;// Jackson supplied class for JSON processing.

	@Test
	public void testSignUpPaperSetter() throws Exception {

		PaperSetter p = new PaperSetter(null, "Sumit", "sumit@gmail", LocalDate.parse("1999-10-10"), "10234569");
		p.setPaperSetterId(2L);
		String json = mapper.writeValueAsString(p);
		//Mockito.when(service.savePaperSetter(Mockito.any(PaperSetter.class))).thenReturn(p);
		mockMvc.perform(post("/papersetter/create").content(json).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
