package com.invesco.qsandas.qsandasrestapi.dao;

import java.util.List;

import com.invesco.qsandas.qsandasrestapi.entity.Answer;

public interface AnswerDAO {

	public Answer createAnswer(Answer answer);

	public Answer findAnswerById(Long answerId);

	public List<Answer> listAllAnswersByQuestionId(Long questionid);

}
