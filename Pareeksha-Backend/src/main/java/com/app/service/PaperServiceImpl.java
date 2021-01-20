package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.PaperRepo;
import com.app.dao.entity.Paper;

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

	@Override
	public Paper findByPaperIdAndPaperPassword(Long id, String password) {
		Paper validatedPaper = repo.findByPaperIdAndPaperPassword(id, password);
		return validatedPaper;
	}

	public String isPaperActive(Paper detachedPaper) {
		// reviewed;endDate;startDate;
		if (detachedPaper.isReviewed()) {
			if (detachedPaper.getStartDate().isBefore(LocalDateTime.now())) {
				if (detachedPaper.getEndDate().isAfter(LocalDateTime.now())) {
					return "This paper is active";
				} else {
					return "Requested Paper has Expired";
				}
			} else {
				return "Requested Paper is not activated yet";
			}
		} else {
			return "Requested Paper is not reviewed yet";
		}
	}

	@Override
	public void updatePaperStatus(Paper detachedPaper) {
		System.out.println("---------------------------------------------------------");
		System.out.println("In save method");
		System.out.println(detachedPaper);
		 repo.save(detachedPaper);
	}

	@Override
	public ArrayList<Paper> findByPaperSetterId(Long paperSetterId) {
		return repo.findByPaperSettterId(paperSetterId);
	}
}
