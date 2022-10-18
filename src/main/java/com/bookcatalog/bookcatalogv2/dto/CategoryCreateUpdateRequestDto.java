package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryCreateUpdateRequestDto implements Serializable {

	private static final long serialVersionUID = 374927498732489L;

	@NotBlank
	private String code;

	@NotBlank
	private String name;

	private String description;
}