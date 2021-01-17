package com.app.unit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.entity.PaperSetter;
import com.app.service.IPaperSetterService;

@SpringBootTest
public class PaperSetterServiceTester {

	@Mock
	IPaperSetterService service;	

	@Test
	public void testSavePaperSetter() {
		PaperSetter p = new PaperSetter(1L, "test", "test@gmail.com", LocalDate.parse("1999-01-01"), "123456789");
		Mockito.when(service.savePaperSetter(p)).thenReturn(p);
	}

	@Test
	public void shouldReturnValidPaperSetter() {
		PaperSetter p = new PaperSetter(1L, "test", "test@gmail.com", LocalDate.parse("1999-01-01"), "123456789");
		Mockito.when(service.getByEmail("test@gmail.com")).thenReturn(p);
		assertEquals("test@gmail.com", p.getEmail());
		
	}
}
