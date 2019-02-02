/**
 * 
 */
package com.brap.schedule.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author prajwbhat
 * @param <T>
 *
 */
public class JenkinsSuccessResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	private Boolean isSuccess;
	private String jobName;
	
	private List<T> responseList = new ArrayList<>();

	/**
	 * @param message
	 * @param isSuccess
	 * @param jobName
	 */
	public JenkinsSuccessResponse(String message, Boolean isSuccess, String jobName) {
		this.message = message;
		this.isSuccess = isSuccess;
		this.jobName = jobName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public List<T> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<T> responseList) {
		this.responseList = responseList;
	}

}
