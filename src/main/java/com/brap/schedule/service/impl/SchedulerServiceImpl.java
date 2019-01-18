package com.brap.schedule.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brap.schedule.client.JenkinsRestClient;
import com.brap.schedule.request.CreateJobRequest;
import com.brap.schedule.service.SchedulerService;
import com.brap.util.CronGeneratorUtil;

@Component
public class SchedulerServiceImpl implements SchedulerService {

	private JenkinsRestClient jenkinsClient;

	private SchedulePersistenceServiceImpl schedulePersistImpl;

	@Autowired
	public SchedulerServiceImpl(JenkinsRestClient client, SchedulePersistenceServiceImpl schedulePersistImpl) {
		this.jenkinsClient = client;
		this.schedulePersistImpl = schedulePersistImpl;
	}

	@Override
	public String buildJob(String jobName) {
		return jenkinsClient.buildJob(jobName);
	}

	@Override
	public String createJob(CreateJobRequest jobRequest) {
		String createdJobName = jenkinsClient.createJob(jobRequest.getJobName(), jobRequest.getConfigXml());
		// get the cron expression for the time period passed
		List<String> cronExpressions = CronGeneratorUtil
				.generateCronExpressionFromInputs(jobRequest.getJobScheduleString());
		jenkinsClient.scheduleJob(jobRequest.getJobName(), cronExpressions);
		schedulePersistImpl.saveJob(jobRequest.getJobName(), jobRequest.getJobScheduleString());
		return createdJobName;
	}
}
