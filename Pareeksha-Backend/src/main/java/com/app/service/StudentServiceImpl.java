package com.app.service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.repo.StudentRepo;
import com.app.dao.repo.entity.Paper;
import com.app.dao.repo.entity.Questions;
import com.app.dao.repo.entity.QuestionsChoices;
import com.app.dao.repo.entity.Student;
import com.app.dto.PaperResultdto;
import com.app.dto.StudentDetailsdto;
import com.app.mapper.StudentMapper;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

	@Autowired
	StudentRepo repo;
	@Autowired
	IPaperService paperService;

	@Override
	public Student createStudentRecord(StudentDetailsdto dto, Student transientStudent) {
		Optional<Paper> detachedPaper = paperService.findById(dto.getPaperId());
		if (detachedPaper.isPresent()) {
			transientStudent = StudentMapper.mapStudentDtoToStudentEntity(dto, transientStudent);
			Paper paper = detachedPaper.get();
			transientStudent.setPaper(paper);
			Student createdStudentRecord = repo.save(transientStudent);
			if (createdStudentRecord != null) {
				return createdStudentRecord;
			} else {
				throw new RuntimeException("Failed to create Student Record please try again");
			}
		} else {
			throw new NoSuchElementException("Sorry Paper You have selected is not found in records");
		}
	}

	@Override
	public String saveStudentsPaperResponse(PaperResultdto resultDto, Questions question,
			QuestionsChoices choice) {
		System.out.println("Printing Result Dto \n=================================================");
		System.out.println(resultDto);
		Optional<Student> fetchedStudent = repo.findById(resultDto.getStudentId());
		if (fetchedStudent.isPresent()) {
			Student student = fetchedStudent.get();
			student.setSubmittedOn(resultDto.getSubmittedOn());
			student.setMarksObtained(resultDto.getMarksObtained());
			student.setSubmittedOn(resultDto.getSubmittedOn());
			repo.save(student);
		} else {
			throw new NoSuchElementException("Error in saving result of student");
		}
		return "Student response saved successfully";
	}

	@Override
	public ArrayList<Student> fetchAllStudentsResults(Long paperId, ArrayList<Student> students) {
		students = repo.findByPaperId(paperId);
		System.out.println(students);
		return students;
	}

}
