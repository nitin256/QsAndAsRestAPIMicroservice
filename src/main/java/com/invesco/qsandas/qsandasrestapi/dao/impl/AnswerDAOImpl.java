package com.invesco.qsandas.qsandasrestapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invesco.qsandas.qsandasrestapi.dao.AnswerDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Answer;
import com.invesco.qsandas.qsandasrestapi.repository.AnswerRepository;

@Service
public class AnswerDAOImpl implements AnswerDAO {

	@Autowired
	AnswerRepository answerRepo;

	@Override
	public Answer createAnswer(Answer answer) {
		return answerRepo.save(answer);
	}

	@Override
	public Answer findAnswerById(Long answerId) {
		return answerRepo.getOne(answerId);
	}

	@Override
	public List<Answer> listAllAnswersByQuestionId(Long questionid) {
		return answerRepo.findAllAnswersByQuestionId(questionid);
	}

}
