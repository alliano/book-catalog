package com.bookcatalog.bookcatalogv2.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherListresponseDto {

	private String publisherId;

	private String publisherName;

	private String comapnyName;
}
