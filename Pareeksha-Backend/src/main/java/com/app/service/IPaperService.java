package com.app.service;

import java.util.Optional;

import com.app.pojos.Paper;

public interface IPaperService {

	public Paper createPaper(Paper transientPaper);
	
	public Optional<Paper> findById(Long id);
}
