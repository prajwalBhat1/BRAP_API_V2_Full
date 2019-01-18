package com.brap.common.response;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {
	JOB_ALREADY_EXISTS("Job already exists on Jenkins with the same name"),
	CONFIG_XML_INVALID("Invalid format of xml file provided . Not parseable "),
	UNKNOWN("UNKNOWN");

	private static Map<String, ErrorCode> MAPPER = Collections.unmodifiableMap(initializeMapper());

	private final String code;

	ErrorCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	// /*
	// * public static ErrorCode parseCode(String errorCodeString) { String
	// * normalizedErrorCodeString = errorCodeString.toUpperCase(); ErrorCode
	// * errorCode = MAPPER.get(normalizedErrorCodeString); return errorCode != null
	// ?
	// * errorCode : UNKNOWN; }
	// */

	private static Map<String, ErrorCode> initializeMapper() {
		Map<String, ErrorCode> mapper = new HashMap<>();
		for (ErrorCode errorCode : values()) {
			mapper.put(errorCode.code, errorCode);
		}
		return mapper;
	}

}
