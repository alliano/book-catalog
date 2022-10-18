package com.bookcatalog.bookcatalogv2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bookcatalog.bookcatalogv2.domain.Publisher;

public interface PublisherRepository extends JpaRepository<com.bookcatalog.bookcatalogv2.domain.Publisher, Long> {

	public java.util.Optional<Publisher> findBySecureId(String secureId);

	public Page<Publisher> findByNameLikeIgnoreCase(String publisherName, Pageable pageable);

	public List<Publisher> findByIdIn(java.util.List<String> publisherIdList);
}
