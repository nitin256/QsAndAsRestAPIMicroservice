package com.invesco.qsandas.qsandasrestapi.dao;

import java.util.List;

import com.invesco.qsandas.qsandasrestapi.entity.Subject;

public interface SubjectDAO {

	public Subject findBySubjectName(String name);

	public Subject addSubject(Subject subject);

	public List<Subject> listAll();

	public Subject findSubjectById(Long subjectId);

}
