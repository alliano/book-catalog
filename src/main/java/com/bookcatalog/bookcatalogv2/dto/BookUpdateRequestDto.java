package com.bookcatalog.bookcatalogv2.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @Data
public class BookUpdateRequestDto {

	@NotBlank(message = "book title is required")
	private String bookTitle;

	@NotBlank(message = "syinopsis is required")
	@JsonProperty(value = "synopsis")
	private String description;
}
