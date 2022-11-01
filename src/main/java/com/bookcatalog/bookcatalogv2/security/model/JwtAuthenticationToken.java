package com.bookcatalog.bookcatalogv2.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * kelas ini digunakan untuk meng custom authentication token jwt
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken{

    private static final long serialVersionUID = 83749274732L;

    private RawAccessJwtToken rawAccessJwtToken;

    private UserDetails userDetails;


    //constructor ini digunakan untu token yang belum di autentikasi
    public JwtAuthenticationToken(RawAccessJwtToken rawAccessJwtToken) {
        super(null);
        this.rawAccessJwtToken = rawAccessJwtToken;
        this.setAuthenticated(true);
    }


    //constructor ini digunakan untuk token yang sudah di autentikasi
    public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.userDetails = userDetails;
        this.userDetails = userDetails;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.rawAccessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    //method ini akan meng overide dan hapus credentials
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.rawAccessJwtToken = null;
    }

}
