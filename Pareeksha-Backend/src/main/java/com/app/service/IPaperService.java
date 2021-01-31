package com.app.service;

import java.util.ArrayList;
import java.util.Optional;

import com.app.dao.entity.Paper;
import com.app.dao.entity.Questions;
import com.app.dao.entity.QuestionsChoices;
import com.app.dto.PaperRequestdto;
import com.app.dto.PaperResponsedto;

public interface IPaperService {

	public Paper createPaper(PaperRequestdto paper,Paper transientPaper );

	public Optional<Paper> findById(Long id);

	public Paper findByPaperIdAndPaperPassword(Long id, String password);

	public String isPaperActive(Paper detachedPaper);

	public String updatePaperStatus(Long paperId);

	public ArrayList<Paper> findByPaperSetterId(Long paperSetterId);
	
	public PaperResponsedto fetchPaper(Long paperId, Paper paper, ArrayList<Questions> questions,
			ArrayList<QuestionsChoices> fetchChoices, PaperResponsedto paperResponse);
	
	public ArrayList<Paper> fetchPublishedPapers(Long paperSetterId);

}
