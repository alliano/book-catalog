package com.bookcatalog.bookcatalogv2.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bookcatalog.bookcatalogv2.validators.annotations.ValidAuthorName;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorCreateRequestDto {

	@ValidAuthorName
	@NotBlank(message = "name is required")
	private String authorName;

	@NotNull(message = "birth date is required")
	private long brithDate;

	@NotEmpty
	private java.util.List<AddressCreateRequestDto> addresses;
}
