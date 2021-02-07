package com.app.unit;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.entity.PaperSetter;
import com.app.dto.PaperSetterdto;
import com.app.mapper.PaperSetterMapper;
import com.app.service.IPaperSetterService;

@SpringBootTest
public class PaperSetterServiceTester {

	@Mock
	IPaperSetterService service;	

	@Test
	public void testSavePaperSetter() {
		PaperSetterdto dto = new PaperSetterdto("test", "test@gmail.com", LocalDate.parse("1999-01-01"), "123456789");
		PaperSetter p = new PaperSetter();
		p=PaperSetterMapper.mapPaperSetterDtoToPapersetterEntity(dto, p);
		p.setPaperSetterId(1L);
		Mockito.when(service.savePaperSetter(dto,p)).thenReturn(p);
		p=service.savePaperSetter(dto, p);
		assertEquals("test@gmail.com", p.getEmail());
	}

	@Test
	public void shouldReturnValidPaperSetter() {
		PaperSetter p = new PaperSetter(1L, "test", "test@gmail.com", LocalDate.parse("1999-01-01"), "123456789");
		Mockito.when(service.getByEmail("test@gmail.com")).thenReturn(p);
		PaperSetter returned = service.getByEmail("test@gmail.com");
		assertEquals("test@gmail.com", returned.getEmail());
		
	}
	
	@Test
	public void shouldReturnPaperSetter() {
		PaperSetter p = new PaperSetter(1L, "test", "test@gmail.com", LocalDate.parse("1999-01-01"), "123456789");
		Optional<PaperSetter> op = Optional.of(p);
		Mockito.when(service.findById(1L)).thenReturn(op);
		Optional<PaperSetter> returned = service.findById(1L);
		assertEquals(returned.get().getName(), "test");
	}
	
	@Test
	public void testChangePassword() {
		PaperSetterdto p = new PaperSetterdto();
		p.setEmail("test@gmail.com");
		p.setDob(LocalDate.parse("1999-01-01"));
		p.setPassword("123456789");
		Mockito.when(service.changePassword(p)).thenReturn("Password updated successfully");
		String returnedString = service.changePassword(p);
		assertEquals("Password updated successfully",returnedString);
	}
}
