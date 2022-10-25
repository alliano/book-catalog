package com.bookcatalog.bookcatalogv2.security.model;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Setter @Getter
public class AccessJwtToken implements Token {

    /**
     * ini nantinya akan berisi token dari user
     * dalam artian belum di decode (token mentah)
     */
    private final String rawToken;

    /**
     * claiims ini berisi statement yang memberikan informasi terhadap sebuah entitas biasanya terkait
     * dengan informasi dari user
     */
    private Claims claims;

    @Override
    public String getToken() {
        return this.rawToken;
    }

}
