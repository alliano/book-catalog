package com.bookcatalog.bookcatalogv2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.dto.UserDetailResponseDto;
import com.bookcatalog.bookcatalogv2.services.ApplicationUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController @RequestMapping(path = "/v1", method = RequestMethod.GET)
public class UserDetailResource {

    private final ApplicationUserService applicationUserService;

    @GetMapping(path = "/user")
    public ResponseEntity<UserDetailResponseDto> findUserDetail() {
        return ResponseEntity.accepted().body(this.applicationUserService.findUserDetail());
    }
}
