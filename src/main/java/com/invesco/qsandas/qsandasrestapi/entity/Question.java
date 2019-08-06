package com.invesco.qsandas.qsandasrestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "QUESTION")
public class Question extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8454652898078626413L;

	public Question() {
	}

	public Question(Long questionId, @NotNull String title, @NotNull Subject subject) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.subject = subject;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QUESTION_ID")
	private Long questionId;

	@NotNull
	@Column(name = "TITLE")
	private String title;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "SUBJECT_ID", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull
	private Subject subject;

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Question [title=").append(title).append(", subject=").append(subject).append("]");
		return builder.toString();
	}

}
