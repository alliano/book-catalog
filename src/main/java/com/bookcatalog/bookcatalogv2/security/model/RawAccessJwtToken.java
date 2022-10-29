package com.bookcatalog.bookcatalogv2.security.model;

import java.security.Key;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

/**
 * kelas ini akan di gunakan utuk merepresentasikan dari token yang dikirimkan oleh client
 * kepada backend
 */


public class RawAccessJwtToken implements Token {

    private String token;

    public RawAccessJwtToken(String token) {
        super();
        this.token = token;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    /**
     * method ini digunakan untuk memparsing token jwt yang diberikan dari clien ke backend
     * diparsing menjadi claims
     *
     * key disini adalah secret key yang disimpan oleh backend
     */
     public Jws<Claims> parseClaims(Key signingKey){
        System.out.println("\n\n\n\n\n\n"+this.token+"\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n"+signingKey.getEncoded().toString()+"\n\n\n\n\n\n");
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(this.token);
     }

}
