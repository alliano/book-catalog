package com.bookcatalog.bookcatalogv2.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookcatalog.bookcatalogv2.domain.Category;
import com.bookcatalog.bookcatalogv2.dto.CategoryQueryDto;

public interface CategoryRepository extends JpaRepository<Category, String>{

	public Optional<Category> findByCode(String code);

	public Page<Category> findByNameLikeIgnoreCase(String name, Pageable pageable);

	public java.util.List<Category> findByCodeIn(java.util.List<String> categoryCodeList);

	// JPA Projection memungkinkan kita untuk mengambil data atau entity itu lintas entity dan kita jadikan kedalam 1 objecr contoh sbg brkt
	// jadi jenis query ini digunakan ketika kita butuh kembalian data dengan tipe yang kita buat dalam kotex ini data yang saya inginkan adalah CategoryQueryDto
	@Query(name = "projecttion_book_categories" ,value = "SELECT new com.bookcatalog.bookcatalogv2.dto.CategoryQueryDto(b.id, b_c.code)"
		+ " FROM Book b"
		+ " INNER JOIN b.categories b_c"
		+ " WHERE b.id IN :bookIdList" )
	public java.util.List<CategoryQueryDto> findCategoryByBookList(java.util.List<Long> bookIdList);

}
