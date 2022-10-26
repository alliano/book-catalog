package com.bookcatalog.bookcatalogv2.security.providers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookcatalog.bookcatalogv2.services.ApplicationUserService;

import lombok.AllArgsConstructor;

// kelas ini digunakna untuk autentication provider
@AllArgsConstructor @Configuration
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    private final ApplicationUserService appUserService;

    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * method ini digunakan untuk mendambahkan logic dari proses authentication nya
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (java.lang.String) authentication.getCredentials();
        UserDetails appUsers = this.appUserService.loadUserByUsername(username);
        if(!this.passwordEncoder.matches(password, appUsers.getPassword())) {
            throw new BadCredentialsException("invalid username password");
        }
        /**
         * ika berhasil maka detail user maka AutenticationProvider akan mengembalikanya ke
         * authenticationFilter dan autetnicationFilter akan membuatkan SecurityContextHolder
         * untuk menyimpan detail user yang diauthentikasi
         * */
        return new UsernamePasswordAuthenticationToken(username, null, appUsers.getAuthorities());
    }

    /**
     * untuk method ini berfungsi untuk menunjukan provider mana yang ingin digunakan bersasarkan
     * jenis token nya
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}
