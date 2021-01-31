package com.app.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.dao.entity.Paper;

public interface PaperRepo extends JpaRepository<Paper, Long> {

	public Paper findByPaperIdAndPaperPassword(Long id,String password);

	@Query("from Paper p where p.paperSetter.paperSetterId=:paperSetterId And p.reviewed=false")
	public ArrayList<Paper> findByPaperSettterIdNotReviwed(@Param("paperSetterId") Long paperSetterId);
	
	//CURRENT_DATE - is evaluated to the current date (a java.sql.Date instance).
	@Query("from Paper p where p.paperSetter.paperSetterId=:paperSetterId And p.reviewed=true And p.startDate < CURRENT_DATE")
	public ArrayList<Paper> findByPaperSettterIdReviwed(@Param("paperSetterId") Long paperSetterId);
}
