package com.brap.persistence.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "brap_file_upload_info")
@Table(name = "brap_file_upload_info")
public class BrapFileUploadInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String jobName;

	private Timestamp createdDate;
    
	private String fileName;

	@Id
	private String fileId;

	public BrapFileUploadInfo() {
		// default
	}

	public BrapFileUploadInfo(String jobName , String fileName) {
		this.jobName = jobName;
		this.createdDate = Timestamp.valueOf(LocalDateTime.now());
		this.fileId = UUID.randomUUID().toString();
		this.fileName = fileName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
    
	@JsonIgnore
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
