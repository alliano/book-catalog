package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorQueryDto implements Serializable {

    private static final long serialVersionUID = -983279476826893L;

    private Long bookId;

    private String authorName;

}
