package com.inditex.prices.application.rest.exception.type;

import com.inditex.prices.application.rest.resources.ErrorMessages;
import com.inditex.prices.application.rest.resources.ExceptionCodes;
import java.io.Serial;

public class MissingFieldException extends BaseException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final String field;

	public MissingFieldException(String field) {
		super(ExceptionCodes.MISSING_FIELD, ErrorMessages.MISSING_FIELD_ERROR_MESSAGE);
		this.field = field;
	}

	public String getField() {
		return field;
	}

}
