package com.bookcatalog.bookcatalogv2.security.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationServiceException;

@Configuration
public class JwtTokenExtractor implements TokenExtractor {

    private final static java.lang.String HEADER_PREFIX = "Barer ";

    @Override
    public String extrac(String payload) {
        if(StringUtils.isBlank(payload)) {
            throw new AuthenticationServiceException("Authorization header can't be blank");
        }

        if (payload.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("invalid Authorization header");
        }
        return payload.substring(HEADER_PREFIX.length(), payload.length());
    }

}
