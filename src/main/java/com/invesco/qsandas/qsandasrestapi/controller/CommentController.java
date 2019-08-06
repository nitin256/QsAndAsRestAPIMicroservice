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
import org.springframework.web.client.ResourceAccessException;

import com.invesco.qsandas.qsandasrestapi.dao.AnswerDAO;
import com.invesco.qsandas.qsandasrestapi.dao.CommentDAO;
import com.invesco.qsandas.qsandasrestapi.dao.QuestionDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Answer;
import com.invesco.qsandas.qsandasrestapi.entity.Comment;
import com.invesco.qsandas.qsandasrestapi.entity.Question;
import com.invesco.qsandas.qsandasrestapi.exception.ResourceNotFoundException;

@RestController
@RequestMapping(ControllerUtils.BASE_URL)
@CrossOrigin
public class CommentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private QuestionDAO questionDAO;

	@Autowired
	private AnswerDAO answerDAO;

	@Autowired
	private CommentDAO commentDAO;

	@PostMapping("/add-comment")
	public Comment addComment(@Valid @RequestBody Comment comment) {

		if ((comment.getQuestion() == null) && (comment.getAnswer() == null)) {
			LOGGER.error("Cannot create comment without a question or answer");
			throw new ResourceAccessException("Cannot create comment without a question or answer");
		}

		Long questionId = null;
		if (comment.getQuestion() != null) {
			questionId = comment.getQuestion().getQuestionId();
		}

		Long answerId = null;
		if (comment.getAnswer() != null) {
			answerId = comment.getAnswer().getAnswerId();
		}

		Question question = null;
		if (questionId != null) {
			question = questionDAO.getQuestionById(questionId);
		}

		Answer answer = null;
		if (answerId != null) {
			answer = answerDAO.findAnswerById(answerId);
		}

		if (question == null && answer == null) {
			LOGGER.error("Cannot create comment without a question or answer");
			throw new ResourceAccessException("Cannot create comment without a question or answer");
		}

		if (question != null) {
			comment.setQuestion(question);
		}

		if (answer != null) {
			comment.setAnswer(answer);
		}

		return commentDAO.createComment(comment);

	}

	@GetMapping("/comment/{commentId}")
	public ResponseEntity<Comment> getComment(@Valid @PathVariable(value = "commentId") Long commentId) {
		Comment comment = commentDAO.getCommentById(commentId);

		if (comment == null) {
			LOGGER.error("No comment found for commentId:[" + commentId + "]");
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(comment);
	}

	@GetMapping("/list-comments/question/{questionId}")
	public List<Comment> getAllCommentsForQuestionId(@Valid @PathVariable(value = "questionId") Long questionId) {
		Question question = questionDAO.getQuestionById(questionId);

		if (question == null) {
			LOGGER.error("No question found for questionId:[" + questionId + "]");
			throw new ResourceAccessException("No question found for questionId:[" + questionId + "]");
		}

		return commentDAO.listAllCommentsForQuestionId(questionId);

	}

	@GetMapping("/list-comments/answer/{answerId}")
	public List<Comment> getAllCommentsForAnswerId(@Valid @PathVariable(value = "answerId") Long answerId) {
		Answer answer = answerDAO.findAnswerById(answerId);

		if (answer == null) {
			LOGGER.error("No answer found for answerId:[" + answerId + "]");
			throw new ResourceNotFoundException("No answer found for answerId:[" + answerId + "]");
		}

		return commentDAO.listAllCommentsForAnswerId(answerId);

	}

}
