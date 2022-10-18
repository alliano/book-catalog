package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherUpdateRequestDto implements Serializable {

	private static final long serialVersionUID = -29547263487268L;

	private String publisherName;

	private String companyName;

	private String address;
}
