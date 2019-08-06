package com.invesco.qsandas.qsandasrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invesco.qsandas.qsandasrestapi.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	@Query("select s from Subject s where s.name = ?1")
	public Subject findByName(String name);

}
