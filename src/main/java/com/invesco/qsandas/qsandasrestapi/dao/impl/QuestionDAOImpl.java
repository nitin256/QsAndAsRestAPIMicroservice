package com.invesco.qsandas.qsandasrestapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invesco.qsandas.qsandasrestapi.dao.QuestionDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Question;
import com.invesco.qsandas.qsandasrestapi.repository.QuestionRepository;

@Service
public class QuestionDAOImpl implements QuestionDAO {

	@Autowired
	QuestionRepository questionRepo;

	@Override
	public Question createQuestion(Question question) {
		return questionRepo.save(question);
	}

	@Override
	public Question getQuestionById(Long questionId) {
		return questionRepo.getOne(questionId);
	}

	@Override
	public List<Question> getAllQuestionsBySubjectId(Long subjectId) {
		return questionRepo.findAllBySubjectId(subjectId);
	}

}
