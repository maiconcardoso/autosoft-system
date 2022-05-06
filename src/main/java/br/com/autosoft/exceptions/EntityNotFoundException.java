package br.com.autosoft.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public static final String MESSAGE = "Entity not Found";

	public EntityNotFoundException(String message) {
		super(message);
	}

}
