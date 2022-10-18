package com.bookcatalog.bookcatalogv2.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorUpdateRequestDto {

	private String authorName;

	private Long birthDate;

	private java.util.List<AddressUpdateRequestDto> addresses;
}
