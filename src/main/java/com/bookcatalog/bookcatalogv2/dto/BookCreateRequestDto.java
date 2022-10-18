package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.bookcatalog.bookcatalogv2.annotations.LogthisArgs;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@LogthisArgs
@Data @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookCreateRequestDto implements Serializable {

	@NotBlank(message = "the title of book is required !")
	private String bookTitle;

	@NotEmpty(message = "author name is required!")
	private java.util.List<String> authorIdList;

	@NotEmpty
	private java.util.List<String> categoryList;

	@NotBlank
	private String publisherId;

	@JsonProperty(value = "synopsis")
	@NotBlank(message = "description is required!")
	private String descriptions;
}
