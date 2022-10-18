package com.bookcatalog.bookcatalogv2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookcatalog.bookcatalogv2.domain.ApplicationUsers;

@Repository
public interface ApplicationUsersRepository extends JpaRepository<ApplicationUsers, Long> {

    public java.util.Optional<ApplicationUsers> findByUsername(String username);
}
