package com.bookcatalog.bookcatalogv2.controllers;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.annotations.LogThisMethod;
import com.bookcatalog.bookcatalogv2.dto.PublisherCreaterequestDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherListresponseDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.services.PublisherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;

@RestController @Validated // ini kita gunakan ketika kita ingin memvalidasi di level parameter nya misal kita ingin memvalidai String name
@AllArgsConstructor @SecurityRequirement(name = "bearerAuth")
public class PublisherResource {

	private final PublisherService publiserService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/v1/publisher")
	public ResponseEntity<Void> createpublisher(@RequestBody @Valid PublisherCreaterequestDto dto) {
		this.publiserService.createPublisher(dto);
		return ResponseEntity.created(URI.create("/v1/publisher")).build();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(path = "/v1/publisher/{secureId}")
	public ResponseEntity<Void> updatePublisher(
		@PathVariable(name = "secureId")
		@Size(min = 36, max = 36, message = "publisher id not uuid") String secureId,
		@Valid @RequestBody PublisherUpdateRequestDto dto) {
		this.publiserService.updatePublisher(secureId, dto);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize(value = "isAuthenticated()")
	@LogThisMethod
	@GetMapping(path = "/v1/publishers")
	public ResponseEntity<ResultPageresponseDto<PublisherListresponseDto>>findByPublisherList(@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit, @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy, @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction, @RequestParam(name = "publisherName", required = false) String publisherName) {
		return ResponseEntity.ok().body(this.publiserService.findPublisherList(pages, limit, sortBy, direction, publisherName));
	}
}
