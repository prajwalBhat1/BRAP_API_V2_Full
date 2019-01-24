/**
 * 
 */
package com.brap.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author prajwbhat
 *
 */
@Configuration
public class PropertyReaderUtil {

	@Value("${jenkins.adminUsername}")
	private String jenkinsUsername;
	@Value("${jenkins.adminPassword}")
	private String jenkinsPassword;
	@Value("${jenkins.port}")
	private String jenkinsPort;
	@Value("${jenkins.url}")
	private String jenkinsUrl;
	@Value("${uploadFiles.path}")
	private String uploadFilePath;

	public String getJenkinsUsername() {
		return jenkinsUsername;
	}

	public String getJenkinsPassword() {
		return jenkinsPassword;
	}

	public String getJenkinsPort() {
		return jenkinsPort;
	}

	public String getJenkinsUrl() {
		return jenkinsUrl;
	}

	public void setJenkinsUrl(String jenkinsUrl) {
		this.jenkinsUrl = jenkinsUrl;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

}
