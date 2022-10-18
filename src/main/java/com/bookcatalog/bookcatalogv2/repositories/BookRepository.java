package com.bookcatalog.bookcatalogv2.repositories;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookcatalog.bookcatalogv2.domain.Book;
import com.bookcatalog.bookcatalogv2.dto.BookQueryDto;

public interface BookRepository extends JpaRepository<Book,Long>{

	public Optional<Book> findById( Long id);

	public Optional<Book> findBySecureId(String secureId);

	@Query(name = "prokection_book_publisher", value = "SELECT DISTINCT"
	+ " new com.bookcatalog.bookcatalogv2.dto.BookQueryDto(b.id, b.secureId, b.title, p.name, b.descriptions)"
	+ " FROM Book b INNER JOIN Publisher p ON (p.id = b.publisher.id) "
	+ " INNER JOIN b.authors b_a "
	+ " WHERE LOWER(p.name) LIKE(CONCAT('%',LOWER(:publisherName),'%'))"
	+ " AND LOWER(b.title) LIKE(CONCAT('%',LOWER(:bookTitle),'%'))"
	+ " AND LOWER(b_a.name) LIKE CONCAT('%',LOWER(:authorName),'%')")
	public Page<BookQueryDto> findBookList(String bookTitle, String publisherName, String authorName, Pageable pageable);

	// public List<Book> findAllBook();

	// public void save(Book book);

	// public void update(Book book);

	// public void delete(Long id);


}
