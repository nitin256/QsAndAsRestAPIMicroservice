package com.invesco.qsandas.qsandasrestapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SUBJECT")
public class Subject extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1898970666954882673L;

	public Subject() {
	}

	public Subject(Long subjectId, @NotNull @Size(max = 100) String name) {
		super();
		this.subjectId = subjectId;
		this.name = name;
	}

	public Subject(Long subjectId, @NotNull @Size(max = 100) String name,
			@NotNull @Size(max = 250) String description) {
		super();
		this.subjectId = subjectId;
		this.name = name;
		this.description = description;
	}

	@Id
	@Column(name = "SUBJECT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subjectId;

	@NotNull
	@Size(max = 100)
	@Column(name = "NAME", unique = true)
	private String name;

	@NotNull
	@Size(max = 250)
	@Column(name = "DESCRIPTION")
	private String description;

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subject [name=").append(name).append(", description=").append(description).append("]");
		return builder.toString();
	}

}
