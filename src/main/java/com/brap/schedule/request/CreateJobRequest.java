package com.brap.schedule.request;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class CreateJobRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jobName;
	private MultipartFile configXml;
	private String jobScheduleString;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public MultipartFile getConfigXml() {
		return configXml;
	}

	public void setConfigXml(MultipartFile configXml) {
		this.configXml = configXml;
	}

	public String getJobScheduleString() {
		return jobScheduleString;
	}

	public void setJobScheduleString(String jobScheduleString) {
		this.jobScheduleString = jobScheduleString;
	}

}
