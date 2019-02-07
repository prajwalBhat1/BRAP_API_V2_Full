package com.brap.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brap.common.response.ResponseView;
import com.brap.schedule.request.CreateJobRequest;
import com.brap.schedule.request.UploadFileRequest;
import com.brap.schedule.response.JenkinsSuccessResponse;
import com.brap.schedule.service.SchedulerService;

/**
 * rest controller to build and create jenkins job
 * 
 * @author prajwbhat
 *
 */
@RestController
@RequestMapping("/brap/api")
public class ScheduleBuildContoller {

	private SchedulerService schedulerService;

	@Autowired
	public ScheduleBuildContoller(SchedulerService schedulerService) {
		this.schedulerService = schedulerService;
	}

	@ResponseBody
	@RequestMapping(value = "/build", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseView<JenkinsSuccessResponse>> triggerBuild(@RequestParam("jobName") String jobName) {
		JenkinsSuccessResponse response = new JenkinsSuccessResponse("Triggering Job Successful", true,
				schedulerService.buildJob(jobName));
		ResponseView<JenkinsSuccessResponse> responseView = new ResponseView<>();
		responseView.setDataContent(response);
		return ResponseEntity.status(HttpStatus.OK).body(responseView);
	}

	@ResponseBody
	@RequestMapping(value = "/createJob", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseView<JenkinsSuccessResponse>> createNewJob(@ModelAttribute CreateJobRequest jobRequest) {
		JenkinsSuccessResponse response = new JenkinsSuccessResponse("Creating Job Successful", true,
				schedulerService.createJob(jobRequest));
		ResponseView<JenkinsSuccessResponse> responseView = new ResponseView<>();
		responseView.setDataContent(response);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseView);
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadFiles", method = { RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseView<JenkinsSuccessResponse>> uploadFiles(@ModelAttribute UploadFileRequest fileRequest) {
		JenkinsSuccessResponse response = new JenkinsSuccessResponse(schedulerService.uploadFiles(fileRequest), true,
				"");
		ResponseView<JenkinsSuccessResponse> responseView = new ResponseView<>();
		responseView.setDataContent(response);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseView);
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadFiles", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseView<JenkinsSuccessResponse>> fetchUploadFilesDetails(@RequestParam("jobName") String jobName) {
		JenkinsSuccessResponse response = new JenkinsSuccessResponse("", true,
				"");
		response.setResponseList(schedulerService.getUploadedFileDetails(jobName));
		ResponseView<JenkinsSuccessResponse> responseView = new ResponseView<>();
		responseView.setDataContent(response);
		return ResponseEntity.status(HttpStatus.OK).body(responseView);
	}
	
	@ResponseBody
	@RequestMapping(value = "/allJobs", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseView<JenkinsSuccessResponse>> getAllJobs() {
		JenkinsSuccessResponse response = new JenkinsSuccessResponse("", true, "");
		response.setResponseList(schedulerService.getAllScheduledJobs());
		ResponseView<JenkinsSuccessResponse> responseView = new ResponseView<>();
		responseView.setDataContent(response);
		return ResponseEntity.status(HttpStatus.OK).body(responseView);
	}
}
