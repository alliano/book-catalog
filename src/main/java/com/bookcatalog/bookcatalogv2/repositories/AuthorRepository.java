package com.bookcatalog.bookcatalogv2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookcatalog.bookcatalogv2.domain.Author;
import com.bookcatalog.bookcatalogv2.dto.AuthorQueryDto;

@Repository
public  interface AuthorRepository extends JpaRepository<com.bookcatalog.bookcatalogv2.domain.Author, Long> {

	//ini tuh sql nya => slelect a from author a where a.id = :id
	public Optional<Author> findById(Long id);

	// => select a from Author a where a.secureId = :secureId
	public Optional<Author> findBySecureId(String secureId);

	// select a from author a where a.id = :id and deleted = false
	public Optional<Author> findByIdAndDeletedFalse(Long id);

	//-> sql nya tuh kek gini SELECT a FROM Author a WHERE a.name LIKE :name
	public java.util.List<Author> findByNameLike(String name);

	public List<Author> findBySecureIdIn(List<String> authorIdList);

	@Query(name = "projection_book_author", value = "SELECT new com.bookcatalog.bookcatalogv2.dto.AuthorQueryDto(b.id, b_a.name)"
		+ " FROM Book b"
		+ " INNER JOIN b.authors b_a"
		+ " WHERE b.id IN :bookIdList")
	public List<AuthorQueryDto> findAuthorsByBookIdList(List<Long> bookIdList);


}
