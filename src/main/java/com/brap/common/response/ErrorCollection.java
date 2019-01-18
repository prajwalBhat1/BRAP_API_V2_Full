package com.brap.common.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorCollection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ErrorMessage> errorList = new ArrayList<>();
	private boolean anyErrors;

	public List<ErrorMessage> getErrors() {
		return errorList;
	}

	public void setErrors(List<ErrorMessage> errorListParam) {
		this.errorList = errorListParam;
	}

	public void addError(ErrorMessage error) {
		if (error == null) {
			throw new IllegalArgumentException(
					"ErrorMessage instance cannot be null when adding to an ErrorCollection instance.");
		}
		errorList.add(error);
	}

	public void addErrorWithoutDuplicates(ErrorMessage error) {
		if (!this.containsErrorCode(error.getErrorCode())) {
			errorList.add(error);
		}
	}

	public boolean isAnyErrors() {
		return !errorList.isEmpty();
	}

	public boolean containsErrorCode(ErrorCode errorCode) {
		ErrorMessage templateErrorMessage = new ErrorMessage(errorCode);
		return errorList.contains(templateErrorMessage);
	}

	public ErrorMessage getSpecificError(ErrorCode errorCode) {
		for (ErrorMessage error : this.errorList) {
			if (error.getErrorCode() == errorCode) {
				return error;
			}
		}
		return null;
	}

	public int size() {
		return errorList.size();
	}

	public void clear(ErrorCode errorCode) {
		if (this.isAnyErrors()) {
			List<ErrorMessage> errorsToRemove = this.errorList.stream()
					.filter(errorMessage -> errorMessage.getErrorCode() == errorCode).collect(Collectors.toList());
			errorList.removeAll(errorsToRemove);
		}
	}

	@Override
	public String toString() {
		String fullErrorString = "";
		for (ErrorMessage errorMessage : errorList) {
			fullErrorString += errorMessage.toString() + " : ";
		}
		return fullErrorString;
	}

	public void clear() {
		errorList.clear();
	}

	public void addAll(ErrorCollection errorCollection) {
		errorList.addAll(errorCollection.getErrors());
	}

	public void removeDuplicatesOf(ErrorCode errorCode) {
		if (containsErrorCode(errorCode)) {
			ErrorMessage errorMessageTemp = getSpecificError(errorCode);
			clear(errorCode);
			addError(errorMessageTemp);
		}
	}
}
