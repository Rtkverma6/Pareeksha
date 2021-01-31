package com.app.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dao.entity.Student;

public interface StudentRepo  extends JpaRepository<Student, Long>{
	
	@Query("from Student s where s.paper.paperId=:paperId")
	public ArrayList<Student> findByPaperId(@Param("paperId") Long paperId);
}
