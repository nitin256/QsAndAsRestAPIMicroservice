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

import com.invesco.qsandas.qsandasrestapi.dao.AnswerDAO;
import com.invesco.qsandas.qsandasrestapi.dao.QuestionDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Answer;
import com.invesco.qsandas.qsandasrestapi.entity.Question;
import com.invesco.qsandas.qsandasrestapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping(ControllerUtils.BASE_URL)
@CrossOrigin
public class AnswerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AnswerController.class);

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@PostMapping("/add-answer")
	@CrossOrigin
	public Answer addAnswer(@Valid @RequestBody Answer answer) {
		if (answer.getQuestion() == null) {
			LOGGER.error("Cannot create an answer without a question");
			throw new ResourceNotFoundException("Cannot create an answer without a question");
		}

		Long questionId = answer.getQuestion().getQuestionId();
		if (questionId == null) {
			LOGGER.error("Cannot create an answer without an invalid questionId");
			throw new ResourceNotFoundException("Cannot create an answer without an invalid questionId");
		}

		Question question = questionDAO.getQuestionById(questionId);

		if (question == null) {
			LOGGER.error("No question found for questionId:[" + questionId + "]");
			throw new ResourceNotFoundException("No question found for questionId:[" + questionId + "]");
		}

		answer.setQuestion(question);

		return answerDAO.createAnswer(answer);
	}

	@GetMapping("/answer/{answerId}")
	@CrossOrigin
	public ResponseEntity<Answer> getAnswerById(@Valid @PathVariable(value = "answerId") Long answerId) {
		Answer answer = answerDAO.findAnswerById(answerId);

		if (answer == null) {
			LOGGER.error("No answer found for Id:[" + answerId + "]");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(answer);

	}

	@GetMapping("/list-answers/{questionId}")
	@CrossOrigin
	public List<Answer> findAllAnswersForQuestionId(@Valid @PathVariable(value = "questionId") Long questionId) {
		Question question = questionDAO.getQuestionById(questionId);

		if (question == null) {
			LOGGER.error("No question found for questionId:[" + questionId + "]");
			throw new ResourceNotFoundException("No question found for questionId:[" + questionId + "]");
		}

		return answerDAO.listAllAnswersByQuestionId(questionId);
	}

}
