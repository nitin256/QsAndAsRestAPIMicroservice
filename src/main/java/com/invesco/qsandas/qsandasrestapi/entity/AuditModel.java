package com.invesco.qsandas.qsandasrestapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdDate", "updatedDate" }, allowGetters = true)
public abstract class AuditModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4061018494105035873L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	@CreatedDate
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE", nullable = false, updatable = false)
	@LastModifiedDate
	private Date updatedDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
