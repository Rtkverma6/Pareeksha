package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Paper;

public interface PaperRepo extends JpaRepository<Paper, Long> {

}
