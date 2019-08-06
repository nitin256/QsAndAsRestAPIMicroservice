package com.invesco.qsandas.qsandasrestapi.dao;

import java.util.List;

import com.invesco.qsandas.qsandasrestapi.entity.Comment;

public interface CommentDAO {

	public Comment createComment(Comment comment);

	public Comment getCommentById(Long commentId);

	public List<Comment> listAllCommentsForQuestionId(Long questionId);

	public List<Comment> listAllCommentsForAnswerId(Long answerId);

}
