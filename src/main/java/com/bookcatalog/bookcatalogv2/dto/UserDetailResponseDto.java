package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailResponseDto implements Serializable {

    private static final long serialVersionUID = -7975468689487L;

    private String username;
}
