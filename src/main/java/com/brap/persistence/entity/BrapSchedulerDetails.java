package com.brap.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "brap_scheduler_details")
@Table(name = "brap_scheduler_details")
public class BrapSchedulerDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column
	private String jobName;
	private String jobCronExpression;
	private Timestamp createdDate;

	/**
	 * @param id
	 * @param jobName
	 * @param jobCronExpression
	 * @param createdDate
	 */

	public BrapSchedulerDetails() {

	}

	public BrapSchedulerDetails(String jobName, String jobCronExpression) {
		this.jobName = jobName;
		this.jobCronExpression = jobCronExpression;
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
		this.id = UUID.randomUUID().toString();
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobCronExpression() {
		return jobCronExpression;
	}

	public void setJobCronExpression(String jobCronExpression) {
		this.jobCronExpression = jobCronExpression;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
}
