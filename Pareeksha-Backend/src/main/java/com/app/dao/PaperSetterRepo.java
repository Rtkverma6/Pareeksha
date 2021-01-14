package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.PaperSetter;

public interface PaperSetterRepo extends JpaRepository<PaperSetter, Long> {

	public PaperSetter findByEmail(String email);
}
