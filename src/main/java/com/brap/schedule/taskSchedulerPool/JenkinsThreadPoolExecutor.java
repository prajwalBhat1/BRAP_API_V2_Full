package com.brap.schedule.taskSchedulerPool;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.brap.schedule.client.JenkinsConnectionUtils;
import com.brap.schedule.service.impl.SchedulePersistenceServiceImpl;
import com.brap.util.CronGeneratorUtil;

@Component
public class JenkinsThreadPoolExecutor {

	@Autowired
	private ThreadPoolTaskSchedulerConfig scheduler;

	@Autowired
	private JenkinsConnectionUtils jenkinsConnectionUtils;

	@Autowired
	private SchedulePersistenceServiceImpl schedulerService;

	@PostConstruct
	public void triggerJobs() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = scheduler.getThreadPoolTaskScheduler();
		threadPoolTaskScheduler.initialize();
		schedulerService.getAllScheduledJobs().stream().forEach(scheduledJob -> {
			List<String> schedules = CronGeneratorUtil
					.generateCronExpressionFromInputs(scheduledJob.getJobCronExpression());
			schedules.stream().forEach(schedule -> {
				CronTrigger trigger = new CronTrigger(schedule);
				threadPoolTaskScheduler.schedule(new JenkinsSchedule(jenkinsConnectionUtils, scheduledJob.getJobName()),
						trigger);
			});
		});
	}
}
