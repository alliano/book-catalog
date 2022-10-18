package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) @Setter @Getter
public class CategoryListResponseDto implements Serializable {

	private final long serialVersionUID = 12837467368L;

	@NotBlank(message = "code is required")
	private String code;

	@NotBlank(message = "name is required")
	private String name;

	private String description;
}
