package io.greenhouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	HttpStatus status;
	String ms;

	public NotFoundException() {
		this.status = HttpStatus.NOT_FOUND;
	}

	NotFoundException(String ms) {
		super(ms);
		this.status = HttpStatus.NOT_FOUND;
	}

	public NotFoundException(HttpStatus notFound, String format) {
		this.status = notFound;
		this.ms = format;
	}
}
