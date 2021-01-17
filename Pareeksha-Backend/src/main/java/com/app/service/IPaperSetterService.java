package com.app.service;

import java.util.Optional;

import com.app.dao.entity.PaperSetter;

public interface IPaperSetterService {
	
	public PaperSetter savePaperSetter(PaperSetter transientObj);
	
	public Optional<PaperSetter> findById(Long id);
	
	public PaperSetter getByEmail(String email);
}
