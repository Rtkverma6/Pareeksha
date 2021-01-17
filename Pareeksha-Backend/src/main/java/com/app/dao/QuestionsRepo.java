package com.app.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dao.entity.Questions;

public interface QuestionsRepo extends JpaRepository<Questions, Long> {

	@Query("from Questions q where q.paper.paperId=:paperId")
	public ArrayList<Questions> fetchAllQuestions(@Param("paperId")  long paperId);
}
