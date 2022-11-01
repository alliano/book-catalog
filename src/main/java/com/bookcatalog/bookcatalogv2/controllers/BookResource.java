package com.bookcatalog.bookcatalogv2.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
// import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.dto.BookCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.BookDetailResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookListResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.services.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController @SecurityRequirement(name = "bearerAuth")
public class BookResource {

	private final BookService bookService;

	@GetMapping(path = "/v1/{secureId}/detail")
	public ResponseEntity<BookDetailResponseDto> findBookBySecureId(@PathVariable(name = "secureId") String secureId) {
		return ResponseEntity.accepted().body(this.bookService.findDetailBySecureId(secureId));
	}

	@PostMapping(path = "/v1/book")
	public ResponseEntity<Void> createNewBook(@RequestBody @Valid BookCreateRequestDto dto) {
		this.bookService.createNewBook(dto);
		return ResponseEntity.created(URI.create("/v1/book")).build();
	}
	@GetMapping(path = "/v1/books")
	public ResponseEntity<java.util.List<BookDetailResponseDto>> findBookList(){
		return ResponseEntity.ok().body(this.bookService.findBookList());
	}
	@PutMapping(path = "/v1/book/{secureId}")
	public ResponseEntity<Void> updateBook(@PathVariable(name = "secureId") String secureId, @RequestBody BookUpdateRequestDto dto) {
		this.bookService.updateBook(secureId, dto);
		return ResponseEntity.created(URI.create("/updated")).build();
	}
	@DeleteMapping(path = "/v1/book/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable(name = "bookId") Long bookId){
		this.bookService.deleteBook(bookId);
		return ResponseEntity.ok().build();
	}
	@GetMapping(path = "/v2/book")
	public ResponseEntity<ResultPageresponseDto<BookListResponseDto>> findBookList(
	 	@RequestParam(name = "page", required = true, defaultValue = "0") Integer page,
		@RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
		@RequestParam(name = "sortBy", required = true, defaultValue = "title") String sortBy,
		@RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
		@RequestParam(name = "bookTitle", required =  false, defaultValue = "") String bookTitle,
		@RequestParam(name = "authorName", required =  false, defaultValue = "") String authorName,
		@RequestParam(name = "publisherName", required = false, defaultValue = "") String publisherName){
		return ResponseEntity.accepted().body(this.bookService.findBookList(page, limit, sortBy, direction, publisherName, authorName, bookTitle));
	}

}
