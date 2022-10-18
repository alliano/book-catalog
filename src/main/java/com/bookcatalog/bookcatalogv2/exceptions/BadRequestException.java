package com.bookcatalog.bookcatalogv2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST) @EqualsAndHashCode(callSuper=false)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -6723784686377L;

	private String message;

	public BadRequestException(String message){
		super();
		this.message = message;
	}
}
