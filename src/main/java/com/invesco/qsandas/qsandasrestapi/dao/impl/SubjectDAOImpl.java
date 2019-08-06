package com.invesco.qsandas.qsandasrestapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invesco.qsandas.qsandasrestapi.dao.SubjectDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Subject;
import com.invesco.qsandas.qsandasrestapi.repository.SubjectRepository;

@Service
public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
	SubjectRepository subjectRepo;

	@Override
	public Subject findSubjectById(Long subjectId) {
		return subjectRepo.getOne(subjectId);
	}

	@Override
	public Subject findBySubjectName(String name) {
		return subjectRepo.findByName(name);
	}

	@Override
	public Subject addSubject(Subject subject) {
		return subjectRepo.save(subject);
	}

	@Override
	public List<Subject> listAll() {
		return subjectRepo.findAll();
	}

}
