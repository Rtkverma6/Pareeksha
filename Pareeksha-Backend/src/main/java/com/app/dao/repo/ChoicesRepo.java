package com.app.dao.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dao.entity.QuestionsChoices;

public interface ChoicesRepo extends JpaRepository<QuestionsChoices, Long>{

	@Query("from QuestionsChoices c where c.question.questionId=:questionId")
	public ArrayList<QuestionsChoices> fetchChoices(@Param("questionId") Long questionId);
}
