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
@Table(name = "ANSWER")
public class Answer extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421447876122391081L;

	public Answer() {
		super();
	}

	public Answer(@NotNull String content, @NotNull Question question) {
		super();
		this.content = content;
		this.question = question;
	}

	public Answer(Long answerId, @NotNull String content, @NotNull Question question) {
		super();
		this.answerId = answerId;
		this.content = content;
		this.question = question;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ANSWER_ID")
	private Long answerId;

	@Column(name = "CONTENT")
	@Lob
	@NotNull
	private String content;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull
	private Question question;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Answer [content=").append(content).append(", question=").append(question).append("]");
		return builder.toString();
	}

}
