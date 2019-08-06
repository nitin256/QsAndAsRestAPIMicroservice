package com.invesco.qsandas.qsandasrestapi.dao;

import java.util.List;

import com.invesco.qsandas.qsandasrestapi.entity.Question;

public interface QuestionDAO {
	
	public Question createQuestion(Question question);
	
	public Question getQuestionById(Long questionId);
	
	public List<Question> getAllQuestionsBySubjectId(Long subjectId);
}
