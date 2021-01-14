package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.QuestionsChoices;

public interface ChoicesRepo extends JpaRepository<QuestionsChoices, Long>{

}
