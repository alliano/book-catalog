package com.bookcatalog.bookcatalogv2.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookcatalog.bookcatalogv2.dto.BookCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.BookDetailResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.services.BookService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor @SecurityRequirement(name = "bearerAuth")
@Controller @Slf4j @RequestMapping(path = "/book")
public class BookController {

	private final BookService bookService;

	@GetMapping(path = "/list")
	public String findAllBook(Model model){
		log.info("endpoin /books has trigered by User");
		model.addAttribute("books", this.bookService.findBookList());

		return "book/list";
	}

	@GetMapping(path = "/new")
	public String loadFormBook(Model model){
		model.addAttribute("book", new BookCreateRequestDto());
		return "book/book-new";
	}

	@PostMapping(path = "/new")
	public String addNewBook(@ModelAttribute("book") @Valid BookCreateRequestDto bookDto,Errors errors, BindingResult binfResult, Model model) {
		if(errors.hasErrors()){
			model.addAttribute("book", bookDto);
			return "book/book-new";
		}
		this.bookService.createNewBook(bookDto);
		return "redirect:/book/list";
	}

	@GetMapping(path = "")
	public ResponseEntity<List<BookDetailResponseDto>> findBookList() {
		return ResponseEntity.ok().body(this.bookService.findBookList());
	}

	@PutMapping(path = "/{secureId}")
	public ResponseEntity<Void> updateBook(@PathVariable(value = "secureId") String secureId, @RequestBody BookUpdateRequestDto dto) {
		this.bookService.updateBook(secureId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") Long id){
		this.bookService.deleteBook(id);
		log.info("User has been deleted data with id "+id);
		return ResponseEntity.ok().build();
	}
}
