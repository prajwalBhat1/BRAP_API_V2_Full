package com.brap.schedule.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brap.util.PropertyReaderUtil;
import com.offbytwo.jenkins.JenkinsServer;
@Component
public class JenkinsConnectionUtils {

	@Autowired
	private PropertyReaderUtil propUtil;
	private static final Logger LOGGER = LoggerFactory.getLogger(JenkinsConnectionUtils.class);

	public JenkinsServer getJenkinsConnection() {
		JenkinsServer jenkins = null;
		try {
			jenkins = new JenkinsServer(new URI(propUtil.getJenkinsUrl()), propUtil.getJenkinsUsername(),
					propUtil.getJenkinsPassword());
		} catch (URISyntaxException e) {
			LOGGER.error(String.format("URI might be malformed : [%s] ", propUtil.getJenkinsUrl()));
		}
		return jenkins;
	}
}
