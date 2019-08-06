package com.invesco.qsandas.qsandasrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import com.invesco.qsandas.qsandasrestapi.dao.QuestionDAO;
import com.invesco.qsandas.qsandasrestapi.dao.SubjectDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Question;
import com.invesco.qsandas.qsandasrestapi.entity.Subject;
import com.invesco.qsandas.qsandasrestapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping(ControllerUtils.BASE_URL)
@CrossOrigin
public class QuestionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private SubjectDAO subjectDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@PostMapping("/add-question")
	public Question createQuestion(@Valid @RequestBody Question question) {
		if (question.getSubject() == null) {
			LOGGER.error("Cannot create question without a subject");
			throw new ResourceNotFoundException("Cannot create a question without a subject");
		}

		String subjectName = question.getSubject().getName();

		if (StringUtils.isBlank(subjectName)) {
			LOGGER.error("Cannot create a question with blank subject");
			throw new ResourceNotFoundException("Subject cannot be blank");
		}

		Subject subject = subjectDAO.findBySubjectName(subjectName);

		if (subject == null) {
			throw new ResourceNotFoundException("No Subject found for:[" + subjectName + "]");
		}

		question.setSubject(subject);

		return questionDAO.createQuestion(question);
	}

	@GetMapping("/question/{questionId}")
	public ResponseEntity<Question> findQuestionByQuestionId(
			@Valid @PathVariable(value = "questionId") Long questionId) {
		Question question = questionDAO.getQuestionById(questionId);

		if (question == null) {
			ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(question);
	}

	@GetMapping("/list-questions/{subjectId}")
	@CrossOrigin
	public List<Question> findAllQuestionsForSubjectId(@Valid @PathVariable(value = "subjectId") Long subjectId) {
		Subject subject = subjectDAO.findSubjectById(subjectId);

		if (subject == null) {
			throw new ResourceNotFoundException("No subject found for Id:[" + subjectId + "]");
		}
		return questionDAO.getAllQuestionsBySubjectId(subjectId);
	}

}
