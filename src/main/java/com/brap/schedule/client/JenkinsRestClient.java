package com.brap.schedule.client;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.brap.common.exception.JobAlreadyExistsException;
import com.brap.common.exception.UnparseableConfigXmlException;
import com.brap.schedule.taskSchedulerPool.JenkinsSchedule;
import com.brap.schedule.taskSchedulerPool.ThreadPoolTaskSchedulerConfig;
import com.offbytwo.jenkins.JenkinsServer;

/**
 * Jenkins Rest Client
 * 
 * @author prajwbhat
 *
 */
@Component
public class JenkinsRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsRestClient.class);
	private ThreadPoolTaskSchedulerConfig scheduler;
	private JenkinsConnectionUtils jenkinsConnectionUtils;

	/**
	 * @param propUtil
	 */
	@Autowired
	public JenkinsRestClient(ThreadPoolTaskSchedulerConfig scheduler, JenkinsConnectionUtils jenkinsConnectionUtils) {
		this.scheduler = scheduler;
		this.jenkinsConnectionUtils = jenkinsConnectionUtils;
	}

	public String buildJob(String jobName) {
		try {
			JenkinsServer jenkins = jenkinsConnectionUtils.getJenkinsConnection();
			jenkins.getJob(jobName).build();
			jenkins.close();
		} catch (IOException e) {
			LOGGER.info("Something Went wrong while connecting to jenkins!!....  " + e.getMessage());
		}
		return jobName;
	}

	/**
	 * Creates the jenkins Job on server
	 * 
	 * @param jobName
	 * @param configXmlPath
	 * @return
	 */
	public String createJob(String jobName, MultipartFile configXmlPath) {
		JenkinsServer jenkins = jenkinsConnectionUtils.getJenkinsConnection();
		try {
			jenkins.createJob(jobName, getXmlInStringFromXmlPath(configXmlPath), false);
		} catch (HttpResponseException ex) {
			if (ex.getStatusCode() == 400) {
				String message = String.format("Looks like the job already exists [%s] ", jobName);
				LOGGER.error(message);
				throw new JobAlreadyExistsException("Job already exists . Try another name ", message);
			}
		} catch (IOException e) {
			LOGGER.info(String.format("Something went wrong when connecting to Jenkins ==  [%s] ", e.getMessage()));
		}
		return jobName;
	}

	public void scheduleJob(String jobName, List<String> cronExpressions) {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = scheduler.getThreadPoolTaskScheduler();
		threadPoolTaskScheduler.initialize();
		cronExpressions.stream().filter(Objects::nonNull).forEach(cronExpression -> {
			threadPoolTaskScheduler.schedule(new JenkinsSchedule(jenkinsConnectionUtils, jobName),
					new CronTrigger(cronExpression));
		});
	}

	private String getXmlInStringFromXmlPath(MultipartFile configXmlPath) {
		try {
			return StreamUtils.copyToString(configXmlPath.getInputStream(), Charset.defaultCharset());
		} catch (IOException e) {
			throw new UnparseableConfigXmlException("Unable to parse the xml to String ",
					"Please provide a valid XML file !!");
		}
	}
}
