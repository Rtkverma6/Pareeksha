package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Questions;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {

}
