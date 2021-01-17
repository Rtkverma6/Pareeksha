package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.entity.Paper;

public interface PaperRepo extends JpaRepository<Paper, Long> {

	public Paper findByPaperIdAndPaperPassword(Long id,String password);

}
