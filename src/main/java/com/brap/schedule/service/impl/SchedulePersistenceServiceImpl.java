package com.brap.schedule.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brap.persistence.entity.BrapSchedulerDetails;
import com.brap.schedule.persistence.repo.BrapSchedulerDetailsRepo;

@Component
public class SchedulePersistenceServiceImpl {
    
	@Autowired
	private BrapSchedulerDetailsRepo scheduleRepo;

	public List<BrapSchedulerDetails> getAllScheduledJobs() {
		return scheduleRepo.findAll();
	}

	public String getCronStringForJob(String jobName) {
		String cronExpr = "";
		Optional<BrapSchedulerDetails> detailsOptional = scheduleRepo.findByJobName(jobName);
		if (detailsOptional.isPresent()) {
			return detailsOptional.get().getJobCronExpression();
		}
		return cronExpr;
	}
    
	public BrapSchedulerDetails saveJob(String jobName, String cronExpression) {
		BrapSchedulerDetails schdulerDetails = new BrapSchedulerDetails(jobName, cronExpression);
		return scheduleRepo.save(schdulerDetails);
	}
}
