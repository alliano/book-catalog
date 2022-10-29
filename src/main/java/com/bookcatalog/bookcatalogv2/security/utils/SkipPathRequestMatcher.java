package com.bookcatalog.bookcatalogv2.security.utils;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SkipPathRequestMatcher implements RequestMatcher {

    private OrRequestMatcher skipMatcher;

    private OrRequestMatcher processingMatcher;

    public SkipPathRequestMatcher(java.util.List<java.lang.String> pathToSkip,java.util.List<java.lang.String> processingPath) {
        java.util.List<RequestMatcher> skip = pathToSkip.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        this.skipMatcher = new OrRequestMatcher(skip);

        java.util.List<RequestMatcher> processing = processingPath.stream().map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        this.processingMatcher = new OrRequestMatcher(processing);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (this.skipMatcher.matches(request)) {
            return false;
        }
        return this.processingMatcher.matches(request) ? true : false;
    }

}
