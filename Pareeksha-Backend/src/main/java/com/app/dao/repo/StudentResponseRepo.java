package com.app.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.repo.entity.StudentResponses;

public interface StudentResponseRepo extends JpaRepository<StudentResponses, Long>{

}
