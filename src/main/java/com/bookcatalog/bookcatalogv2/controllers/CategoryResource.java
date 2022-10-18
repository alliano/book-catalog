package com.bookcatalog.bookcatalogv2.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookcatalog.bookcatalogv2.dto.CategoryCreateUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.CategoryListResponseDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.services.CategoryService;

import lombok.AllArgsConstructor;

@RestController @AllArgsConstructor
public class CategoryResource {

	private final CategoryService categoryService;

	@PostMapping(path = "/v1/category")
	public ResponseEntity<Void> createAndUpdateCategory(@RequestBody CategoryCreateUpdateRequestDto dto){
		this.categoryService.createAndUpdateCategory(dto);
		return ResponseEntity.created(URI.create("/v1/category")).build();
	}

	@GetMapping(path = "v1/category")
	public ResponseEntity<ResultPageresponseDto<CategoryListResponseDto>> findcategoryList(@RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages, @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit, @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy, @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction, @RequestParam(name = "publisherName", required = false) String categoryName){
		return ResponseEntity.ok().body(this.categoryService.findCategoryList(pages, limit, sortBy, direction, categoryName));
	}
}
