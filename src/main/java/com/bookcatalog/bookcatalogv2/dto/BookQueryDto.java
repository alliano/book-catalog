package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor @Setter
public class BookQueryDto implements Serializable {

    private static final long serialVersionUID = -349832749723L;

    private Long id;

    private String bookId;

    private String bookTitle;

    private String publisherName;

    private String description;
}
