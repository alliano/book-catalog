package com.bookcatalog.bookcatalogv2.dto;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter @Getter
public class BookListResponseDto {

    private String id;

    private String title;

    private String description;

    private String publisherName;

    private List<String> catagoryCodes;

    private List<String> authorNames;

}
