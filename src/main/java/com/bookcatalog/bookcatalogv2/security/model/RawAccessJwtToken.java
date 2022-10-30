package com.bookcatalog.bookcatalogv2.security.model;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
/**
 * kelas ini digunakan untuk merepresentasikan dari token yang dikirimkan oleh client
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
     * key disini adalah secret key yang disimpan oleh backend dalam context ini saya menggunakan
     * bean. bisa di cek di class AppConf.java
     */
    public Jws<Claims> parseClaims(Key signingKey){
		return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(this.token);
	}

}
