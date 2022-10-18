package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PublisherCreaterequestDto implements Serializable {

	private static final long serialVersionUID  = -389449577832487L;

	@NotBlank(message = "publisher name is required")
	private String publisherName;

	@NotBlank(message = "company name is required")
	private String companyName;

	private String address;
}
