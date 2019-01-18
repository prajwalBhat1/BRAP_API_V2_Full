package com.brap.authentication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventCreator {

	private static final Logger LOG = LoggerFactory.getLogger(EventCreator.class);

	public EventCreator() {
	}

	@Scheduled(cron = "0 07 22 * * *")
	public void create() {
		LOG.debug("Event created!");
		System.out.println("Triggered!!");
	}

	public static void main(String[] args) {
		EventCreator creator = new EventCreator();
	}

}
