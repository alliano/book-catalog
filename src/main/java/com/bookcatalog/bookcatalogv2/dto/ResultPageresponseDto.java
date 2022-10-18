package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResultPageresponseDto<T> implements Serializable {

	private final long serialVersionUID = -84657943972378L;

	private java.util.List<T> result = new ArrayList<>();

	private long page;

	private long elements;
}
