package com.bookcatalog.bookcatalogv2.security.utils;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import com.bookcatalog.bookcatalogv2.security.model.AccessJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtTokenFactory {

    private final Key key;

    public AccessJwtToken createAccessJwtToken(java.lang.String username, java.util.Collection<? extends GrantedAuthority> authorities) {
        Claims claims = Jwts.claims().setSubject(username);
        //scope ini berisi role dari username tersebut
        claims.put("scope", authorities.stream().map( a -> a.getAuthority()).collect(Collectors.toList()));
        //waktu kapan token ini di generate dan kapan expiered dari token ini
        LocalDateTime curentTime = LocalDateTime.now();
        LocalDateTime expieredTime = curentTime.plusMinutes(15L);

        Date curentDateTime = Date.from(curentTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());
        Date expierdTimeDate = Date.from(expieredTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant());

        //generate token
        java.lang.String token = Jwts.builder().setClaims(claims)
        .setIssuer("http://alliano.com").setIssuedAt(curentDateTime)
        .setExpiration(expierdTimeDate).signWith(key,SignatureAlgorithm.HS256).compact();
        return new AccessJwtToken(token, claims);
    }
}
