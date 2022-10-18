package com.bookcatalog.bookcatalogv2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/**
 * GrantedAuthority adalah interface dari spring security yang mana nanti digunakan
 * untuk mendefinisikan role apa atau autorization apa yang nantinya dimiliki oleh
 * seorang user
 */
@Setter @Getter @Entity @Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return "ROLE_"+this.name;
    }

}
