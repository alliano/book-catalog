package com.bookcatalog.bookcatalogv2.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookcatalog.bookcatalogv2.dto.UserDetailResponseDto;

/**
 * UserDetailsService ini adalah interface core dari sprig security yang berguna untuk
 * meng load spesifik user
 */
public interface ApplicationUserService extends UserDetailsService {

    public UserDetailResponseDto findUserDetail();
}
