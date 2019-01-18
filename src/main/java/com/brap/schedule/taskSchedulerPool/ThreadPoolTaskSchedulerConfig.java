package com.brap.schedule.taskSchedulerPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadPoolTaskSchedulerConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadPoolTaskSchedulerConfig.class);

	@Bean
	public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		threadPoolTaskScheduler.setThreadNamePrefix("JenkinsThreadPoolTaskScheduler");
		LOGGER.info("Successfully created the ThreadPoolExecutor Bean");
		return threadPoolTaskScheduler;
	}
}
