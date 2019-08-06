package com.invesco.qsandas.qsandasrestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "COMMENT")
public class Comment extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7761435603699835452L;

	public Comment() {
		super();
	}

	public Comment(@NotNull String text, Question question, Answer answer) {
		super();
		this.text = text;
		this.question = question;
		this.answer = answer;
	}

	public Comment(@NotNull String text, Answer answer) {
		super();
		this.text = text;
		this.answer = answer;
	}

	public Comment(@NotNull String text, Question question) {
		super();
		this.text = text;
		this.question = question;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	private Long commentId;

	@Column(name = "TEXT")
	@Lob
	@NotNull
	private String text;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "QUESTION_ID", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Question question;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "ANSWER_ID", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Answer answer;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [text=").append(text).append(", question=").append(question).append(", answer=")
				.append(answer).append("]");
		return builder.toString();
	}

}
