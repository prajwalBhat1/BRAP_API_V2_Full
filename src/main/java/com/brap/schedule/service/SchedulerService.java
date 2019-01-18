package com.brap.schedule.service;

import org.springframework.web.multipart.MultipartFile;

import com.brap.schedule.request.CreateJobRequest;

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
}
