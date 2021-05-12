package br.com.cadastroproduto.exception.handler.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {

	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path,
			String messsageDev) {
		super(timestamp, status, error, message, path, messsageDev);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
