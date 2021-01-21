package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.entity.StudentResponses;

public interface StudentResponseRepo extends JpaRepository<StudentResponses, Long>{

}
