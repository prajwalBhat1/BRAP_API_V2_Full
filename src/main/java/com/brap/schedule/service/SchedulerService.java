package com.brap.schedule.service;

import com.brap.schedule.request.CreateJobRequest;
import com.brap.schedule.request.UploadFileRequest;

/**
 * Interface describes the various jenkins actions
 * 
 * @author prajwbhat
 *
 */
public interface SchedulerService {

	/**
	 * Triggers a build for an existing job
	 * 
	 * @param jobName
	 */
	String buildJob(String jobName);

	/**
	 * Creates a job on jenkins using the provided config xml and with the given job
	 * name
	 * 
	 * @param jobName
	 * @param configXml
	 * @return
	 */
	String createJob(CreateJobRequest jobRequest);

	/**
	 * Upload file on the specified path given
	 * 
	 * @param fileRequest
	 * @return
	 */
	String uploadFiles(UploadFileRequest fileRequest);
}
