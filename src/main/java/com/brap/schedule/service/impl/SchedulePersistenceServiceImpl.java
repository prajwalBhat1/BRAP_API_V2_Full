package com.brap.schedule.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.brap.persistence.entity.BrapFileUploadInfo;
import com.brap.persistence.entity.BrapSchedulerDetails;
import com.brap.schedule.persistence.repo.BrapFileUploadInfoRepo;
import com.brap.schedule.persistence.repo.BrapSchedulerDetailsRepo;

@Component
public class SchedulePersistenceServiceImpl {

	@Autowired
	private BrapSchedulerDetailsRepo scheduleRepo;

	@Autowired
	private BrapFileUploadInfoRepo fileUploadRepo;

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

	public List<BrapFileUploadInfo> saveUploadFileDetails(Map<String, MultipartFile> fileMap, String jobName) {
		List<BrapFileUploadInfo> infoList = new ArrayList<>();
		fileMap.keySet().stream().forEach(info -> {
			BrapFileUploadInfo fileUploadObj = new BrapFileUploadInfo(jobName, info);
			infoList.add(fileUploadObj);
		});
		return fileUploadRepo.saveAll(infoList);
	}
	
	public List<BrapFileUploadInfo> getFileInfosByJobName(String jobName) {
		return fileUploadRepo.findByJobName(jobName);
	}
}
