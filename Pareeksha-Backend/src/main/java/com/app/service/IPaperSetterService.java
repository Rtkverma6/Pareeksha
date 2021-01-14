package com.app.service;

import java.util.Optional;

import com.app.pojos.PaperSetter;

public interface IPaperSetterService {
	public PaperSetter savePaperSetter(PaperSetter transientObj);
	
	public Optional<PaperSetter> findById(Long id);
}
