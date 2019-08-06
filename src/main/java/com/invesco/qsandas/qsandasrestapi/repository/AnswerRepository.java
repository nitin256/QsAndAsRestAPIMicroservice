package com.invesco.qsandas.qsandasrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invesco.qsandas.qsandasrestapi.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

	@Query("select a from Answer a where a.question.id = ?1")
	public List<Answer> findAllAnswersByQuestionId(Long questionId);

}
