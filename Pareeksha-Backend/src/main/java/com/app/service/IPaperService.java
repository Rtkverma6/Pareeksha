package com.app.service;

import java.util.Optional;

import com.app.dao.entity.Paper;

public interface IPaperService {

	public Paper createPaper(Paper transientPaper);
	
	public Optional<Paper> findById(Long id);
	
	public Paper findByPaperIdAndPaperPassword(Long id,String password);
	
	public String isPaperActive(Paper detachedPaper);
}
