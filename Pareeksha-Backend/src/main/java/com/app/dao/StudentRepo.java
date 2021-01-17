package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.dao.entity.Student;

public interface StudentRepo  extends JpaRepository<Student, Long>{

}
