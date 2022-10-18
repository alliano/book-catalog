package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter @Getter @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryQueryDto implements Serializable {

    private final long serialVersionUID = -74932497682L;

    private Long bookId;

    private String categoryCode;
}
