package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.Setter;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter @Getter
public class AddressUpdateRequestDto implements Serializable {

    private static final long serialVersionUID = -34393748348L;

    private Long addressId;

    private String streetName;

    private String cityName;

    private String zipCode;


}
