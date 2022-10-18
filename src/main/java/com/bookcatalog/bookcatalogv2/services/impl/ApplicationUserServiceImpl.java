package com.bookcatalog.bookcatalogv2.services.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.dto.UserDetailResponseDto;
import com.bookcatalog.bookcatalogv2.repositories.ApplicationUsersRepository;
import com.bookcatalog.bookcatalogv2.services.ApplicationUserService;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUsersRepository applicationUsersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.applicationUsersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("invalid username"));
    }

    @Override
    public UserDetailResponseDto findUserDetail() {
        SecurityContext secururiryContextHolder = SecurityContextHolder.getContext();
        UserDetailResponseDto dto = new UserDetailResponseDto();
        dto.setUsername(secururiryContextHolder.getAuthentication().getName());
        return dto;
    }

}
