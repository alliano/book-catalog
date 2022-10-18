package com.bookcatalog.bookcatalogv2.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginRequestDto implements Serializable {

    private static final long serialVersionUID = -3804732749732L;

    private String username;

    private String password;
}
