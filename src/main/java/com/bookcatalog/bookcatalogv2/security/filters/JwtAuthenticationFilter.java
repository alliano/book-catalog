package com.bookcatalog.bookcatalogv2.security.filters;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.bookcatalog.bookcatalogv2.security.model.JwtAuthenticationToken;
import com.bookcatalog.bookcatalogv2.security.model.RawAccessJwtToken;
import com.bookcatalog.bookcatalogv2.security.utils.TokenExtractor;


/**
 * kelas ini digunakan untuk meng intercept request yang masuk dan kemudian akan mendelegasikan
 * kepada AuthenticationProvieder untuk mengvalidasi apakah token yang disertakan oleh client tersebut
 * disetiap request itu valid atau nga
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private TokenExtractor tokenExtractor;

    protected JwtAuthenticationFilter(TokenExtractor tokenExtractor ,RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        /**
         * disini kita ambil token jwt nya dari header
         * biasanya
         *
         * format dari tokenPayload nanti adalah barer.tokenya
         * exp : barer.xxxx.yyyy.sss
         */
        String tokenPayload = request.getHeader("Authorization");
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extrac(tokenPayload));
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

}
