package com.bookcatalog.bookcatalogv2.web;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.dto.BookCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResponseMessage;
import com.bookcatalog.bookcatalogv2.services.BookService;
import com.bookcatalog.bookcatalogv2.services.GrettingService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController @Slf4j
public class HelloWolrd {

	private final GrettingService grettingService;

	private final BookService bookService;

	@GetMapping(path = "/hello")
	public ResponseEntity<ResponseMessage> hello() {
		log.info("endpoin /hello has trigered!");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(this.grettingService.sayHello()));
	}

	@PostMapping(path = "/book")
	public ResponseEntity<Void> createBook(@RequestBody BookCreateRequestDto book) {
		this.bookService.createNewBook(book);
		return ResponseEntity.created(URI.create("/book")).build();
	}
}
