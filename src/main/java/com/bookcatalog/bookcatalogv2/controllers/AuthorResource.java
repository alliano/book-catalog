package com.bookcatalog.bookcatalogv2.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.dto.AuthorCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorResponseDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.services.AuthorService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@Validated @SecurityRequirement(name = "bearerAuth")
@RestController @AllArgsConstructor
public class AuthorResource {

	private final AuthorService authorService;

	@GetMapping(path = "/v1/author/{secureId}/detail")
	public ResponseEntity<AuthorResponseDto> findAuthorBySecureId(@PathVariable(name = "secureId") String secureId){
		return ResponseEntity.status(HttpStatus.OK).body(this.authorService.findAuthorBySecureId(secureId));
	}

	@PostMapping(path = "/v1/author")
	public ResponseEntity<Void> createNewAuthor(@RequestBody @Valid java.util.List<AuthorCreateRequestDto> dtos)  {
		this.authorService.createNewAuthor(dtos);
		return ResponseEntity.created(URI.create("/author")).build();
	}

	@PutMapping( path = "/v1/author/{secureId}")
	public ResponseEntity<Void> updateAuthor(@RequestBody @Valid AuthorUpdateRequestDto dto, @PathVariable(name = "secureId") String secureId) {
		this.authorService.updateAuthor(secureId, dto);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/v1/author/{secureId}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable(name = "secureId") String secureId){
		this.authorService.deleteAuthor(secureId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
