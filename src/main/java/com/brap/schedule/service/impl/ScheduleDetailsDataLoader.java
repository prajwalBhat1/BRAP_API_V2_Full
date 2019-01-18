/**
 * 
 */
package com.brap.schedule.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.brap.persistence.entity.BrapSchedulerDetails;
import com.brap.schedule.persistence.repo.BrapSchedulerDetailsRepo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author prajwbhat
 *
 */
@Component
@Profile({ "default" })
public class ScheduleDetailsDataLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleDetailsDataLoader.class);
	@Autowired
	private BrapSchedulerDetailsRepo detailsRepo;

	private ObjectMapper objectMapper = new ObjectMapper();

	private static final String APP_LOADER_FILE = "data/brapScheduleDetails.json";

	@PostConstruct
	public void loadScheduleDetailsSample() {
		try {
			List<BrapSchedulerDetails> schedulerDetails = loadScheduleDetails();
			schedulerDetails.stream().forEach(schedule -> {
				BrapSchedulerDetails schedulerDetail = new BrapSchedulerDetails(schedule.getJobName(),
						schedule.getJobCronExpression());
				detailsRepo.save(schedulerDetail);
			});
		} catch (Exception e) {
			LOGGER.info("Exception caught . Failed to load data !! ==   " + e.getMessage());
		}
	}

	private List<BrapSchedulerDetails> loadScheduleDetails()
			throws JsonParseException, JsonMappingException, IOException {
		detailsRepo.deleteAll();
		InputStream benefitsInputStream = new ClassPathResource(APP_LOADER_FILE).getInputStream();
		LOGGER.info("Reading schedule details from json...");
		return objectMapper.readValue(benefitsInputStream, new TypeReference<List<BrapSchedulerDetails>>() {
		});
	}
}
