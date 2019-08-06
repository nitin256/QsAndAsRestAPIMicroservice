package com.invesco.qsandas.qsandasrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invesco.qsandas.qsandasrestapi.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("Select q from Question q where q.subject.id = ?1")
	public List<Question> findAllBySubjectId(Long subjectId);

}
