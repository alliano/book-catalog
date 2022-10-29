package com.bookcatalog.bookcatalogv2.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.bookcatalog.bookcatalogv2.security.model.AnonymousAuthentication;
import com.bookcatalog.bookcatalogv2.security.model.JwtAuthenticationToken;
import com.bookcatalog.bookcatalogv2.security.model.RawAccessJwtToken;
import com.bookcatalog.bookcatalogv2.security.utils.TokenExtractor;


/**
 * kelas ini digunakan untuk meng intercept request yang masuk dan kemudian akan mendelegasikan
 * kepada AuthenticationProvieder untuk mengvalidasi apakah token yang disertakan oleh client tersebut
 * disetiap request itu valid atau nga
 *
 * pada dasarnya kelas ini mirip dengan kelas UsernamePasswordAuthenticationFilter
 *
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private TokenExtractor tokenExtractor;

    private AuthenticationFailureHandler failureHandler;

    public JwtAuthenticationFilter(AuthenticationFailureHandler failureHandler ,TokenExtractor tokenExtractor ,RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        this.tokenExtractor = tokenExtractor;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        /**
         * disini kita ambil token jwt nya dari header
         * biasanya key untuk header nya adalah Authorization
         *
         * format dari tokenPayload nanti adalah barer.tokenya
         * exp : barer.xxxx.yyyy.sss
         */
        String tokenPayload = request.getHeader("Authorization");
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extrac(tokenPayload));
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    //method ini akan di eksekusi jikalau proses Authentikasi berhasil
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //jika berhasil kita buat security context dengan contect kosong
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        //setelah dibuat security context lalu kita set context nya dengan Authentication
        context.setAuthentication(authResult);
        //set context(simpan userdetail ke dalam securityContextholder)
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }


    @Override// method ini akan di panggil ketika proses Authentikasi gagal
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //jika Authentikasi gagal maka bersihkan contextHolder
        SecurityContextHolder.clearContext();
        //set contextHolder nya dengan autentikasi kosong
        SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());
        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }

}
