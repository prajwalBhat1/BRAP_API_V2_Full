package com.brap.schedule.taskSchedulerPool;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brap.schedule.client.JenkinsConnectionUtils;

public class JenkinsSchedule implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsSchedule.class);

	private String jobName;
	private JenkinsConnectionUtils jenkinsConnectionUtils;

	public JenkinsSchedule(JenkinsConnectionUtils jenkinsConnectionUtils, String jobName) {
		this.jenkinsConnectionUtils = jenkinsConnectionUtils;
		this.jobName = jobName;
	}

	@Override
	public void run() {
		LOGGER.info("Triggering the job via Scheduler for the job == " + jobName);
		try {
			jenkinsConnectionUtils.getJenkinsConnection().getJob(jobName).build();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
