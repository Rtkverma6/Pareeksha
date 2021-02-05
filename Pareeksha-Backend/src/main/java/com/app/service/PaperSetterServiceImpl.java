package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.entity.PaperSetter;
import com.app.dao.repo.PaperSetterRepo;
import com.app.dto.PaperSetterdto;
import com.app.exception.DataIntegrityViolationException;
import com.app.exception.IllegalArgumentException;
import com.app.exception.NoSuchElementException;
import com.app.mapper.PaperSetterMapper;

@Service
@Transactional
public class PaperSetterServiceImpl implements IPaperSetterService {

	@Autowired
	PaperSetterRepo repo;
	@Autowired
	PasswordEncoder encoder;

	@Override
	public PaperSetter savePaperSetter(PaperSetterdto dto, PaperSetter transientObj) {
		transientObj = PaperSetterMapper.mapPaperSetterDtoToPapersetterEntity(dto, transientObj);
		System.out.println(transientObj);
		try {
			transientObj.setPassword(encoder.encode(transientObj.getPassword()));
			PaperSetter persistentObj = repo.save(transientObj);
			if (persistentObj != null) {
				return persistentObj;
			} else {
				throw new IllegalArgumentException("Failed to Signup...");
			}
		} catch (RuntimeException e) {
			System.out.println("err in save " + e);
			throw new DataIntegrityViolationException("Email already exists in database Please try another mailId");
		}
	}

	@Override
	public Optional<PaperSetter> findById(Long id) {
		Optional<PaperSetter> paperSetter = repo.findById(id);
		return paperSetter;
	}

	@Override
	public PaperSetter getByEmail(String email) {
		PaperSetter fetchedEmail = repo.findByEmail(email);
		return fetchedEmail;
	}

	@Override
	public String changePassword(PaperSetterdto details) {
		PaperSetter fetchedPaperSetter = repo.findByEmailAndDob(details.getEmail(), details.getDob());
		if (fetchedPaperSetter != null) {
			fetchedPaperSetter.setPassword(encoder.encode(details.getPassword()));
			repo.save(fetchedPaperSetter);
		}else {
			throw new NoSuchElementException("Please check your credentials.....");
		}
		return "Password updated successfully";
	}
}
