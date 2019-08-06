package com.invesco.qsandas.qsandasrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invesco.qsandas.qsandasrestapi.dao.SubjectDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Subject;

@RestController
@RequestMapping(ControllerUtils.BASE_URL)
@CrossOrigin
public class SubjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);

	@Autowired
	private SubjectDAO subjectDAO;

	@PostMapping("/add-subject")
	public Subject addSubject(@Valid @RequestBody Subject subject) {
		return subjectDAO.addSubject(subject);
	}

	@GetMapping("/list-subjects")
	@CrossOrigin
	public List<Subject> listAllSubjects() {
		return subjectDAO.listAll();
	}

	@GetMapping("/subject/{name}")
	public ResponseEntity<Subject> findByName(@PathVariable(value = "name") String name) {

		Subject subject = subjectDAO.findBySubjectName(name);

		if (subject == null) {
			LOGGER.warn("No subject found for name:[" + name + "]");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(subject);

	}

}
