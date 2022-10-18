package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookDetailResponseDto implements Serializable {

	private final long serialVersionUID = -12348638478L;

	private String bookId;

	private List<AuthorResponseDto> authors;

	private List<CategoryListResponseDto> categories;

	private PublisherResponseDto publisher;

	private String bookTitle;

	private String bookDescriptions;


}
