package com.inditex.prices.application.rest.exception.type;

import com.inditex.prices.application.rest.resources.ErrorMessages;
import com.inditex.prices.application.rest.resources.ExceptionCodes;

public class DataNotFoundException extends BaseException {
	private static final long serialVersionUID = 1L;

	private final String description;

	public DataNotFoundException(String description) {
		super(ExceptionCodes.DATA_NOT_FOUND, ErrorMessages.NOT_FOUND_ERROR_MESSAGE);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
