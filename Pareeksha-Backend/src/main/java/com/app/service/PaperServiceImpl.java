package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PaperRepo;
import com.app.pojos.Paper;

@Service
@Transactional
public class PaperServiceImpl implements IPaperService {

	@Autowired
	PaperRepo repo;
	
	@Override
	public Paper createPaper(Paper transientPaper) {
		Paper createdPaper = repo.save(transientPaper);
		return createdPaper;
	}

	@Override
	public Optional<Paper> findById(Long id) {
		return repo.findById(id);
	}

}
