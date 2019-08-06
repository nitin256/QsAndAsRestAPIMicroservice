package com.invesco.qsandas.qsandasrestapi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invesco.qsandas.qsandasrestapi.dao.CommentDAO;
import com.invesco.qsandas.qsandasrestapi.entity.Comment;
import com.invesco.qsandas.qsandasrestapi.repository.CommentRepository;

@Service
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private CommentRepository commentRepo;

	@Override
	public Comment createComment(Comment comment) {
		return commentRepo.save(comment);
	}

	@Override
	public Comment getCommentById(Long commentId) {
		return commentRepo.getOne(commentId);
	}

	@Override
	public List<Comment> listAllCommentsForQuestionId(Long questionId) {
		return commentRepo.getAllCommentsForQuestionId(questionId);
	}

	@Override
	public List<Comment> listAllCommentsForAnswerId(Long answerId) {
		return commentRepo.getAllCommentsForAnswerId(answerId);
	}

}
