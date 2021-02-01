package com.app.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.repo.entity.PaperSetter;

public interface PaperSetterRepo extends JpaRepository<PaperSetter, Long> {

	public PaperSetter findByEmail(String email);
}
