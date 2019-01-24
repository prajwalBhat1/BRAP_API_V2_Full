/**
 * 
 */
package com.brap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.brap.common.exception.JobAlreadyExistsException;
import com.brap.common.exception.UnableToUploadFileException;
import com.brap.common.exception.UnparseableConfigXmlException;
import com.brap.common.response.ErrorCode;
import com.brap.common.response.ErrorCollection;
import com.brap.common.response.ErrorMessage;
import com.brap.common.response.ResponseView;

/**
 * @author prajwbhat
 *
 */
@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(JobAlreadyExistsException.class)
	@ResponseBody
	public ResponseEntity<ResponseView<Object>> handleJobAlreadyExistsException(JobAlreadyExistsException ex) {
		ResponseView<Object> responseView = buildResponseViewForException(ErrorCode.JOB_ALREADY_EXISTS,
				"Job with a given name already exists . Please try a different name !!");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseView);
	}

	@ExceptionHandler(UnparseableConfigXmlException.class)
	@ResponseBody
	public ResponseEntity<ResponseView<Object>> handleUnparseableConfigXmlException(UnparseableConfigXmlException ex) {
		ResponseView<Object> responseView = buildResponseViewForException(ErrorCode.CONFIG_XML_INVALID,
				"Please upload a valid configuration XML file!!");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseView);
	}
	
	@ExceptionHandler(UnableToUploadFileException.class)
	@ResponseBody
	public ResponseEntity<ResponseView<Object>> handleUnableToUploadFileException(UnableToUploadFileException ex) {
		ResponseView<Object> responseView = buildResponseViewForException(ErrorCode.UNABLE_TO_UPLOAD_FILES,
				"No files were found or upload failed Please try again!!!");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseView);
	}

	/**
	 * @return
	 */
	public ResponseView<Object> buildResponseViewForException(ErrorCode code, String errorMessage) {
		ResponseView<Object> responseView = new ResponseView<>();
		ErrorCollection collection = new ErrorCollection();
		ErrorMessage message = new ErrorMessage(code, errorMessage);
		List<ErrorMessage> errorMsgList = new ArrayList<>();
		errorMsgList.add(message);
		collection.setErrors(Collections.unmodifiableList(errorMsgList));
		responseView.setErrorContent(collection);
		return responseView;
	}
}
