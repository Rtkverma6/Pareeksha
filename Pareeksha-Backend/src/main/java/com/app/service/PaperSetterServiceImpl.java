package com.app.service;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.AbstractDataBufferDecoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.PaperSetterRepo;
import com.app.pojos.PaperSetter;

@Service
@Transactional
public class PaperSetterServiceImpl implements IPaperSetterService {

	@Autowired
	PaperSetterRepo repo;
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public PaperSetter savePaperSetter(PaperSetter transientObj) {
		transientObj.setPassword(encoder.encode(transientObj.getPassword()));
		PaperSetter persistentObj = repo.save(transientObj);
		System.out.println("In savePaperSetter()");
		System.out.println(persistentObj);
		return persistentObj;
	}

	@Override
	public Optional<PaperSetter> findById(Long id) {
		Optional<PaperSetter> paperSetter = repo.findById(id);
		return paperSetter;
	}


}
