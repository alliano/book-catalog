package com.bookcatalog.bookcatalogv2.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.domain.Category;
import com.bookcatalog.bookcatalogv2.dto.CategoryCreateUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.CategoryListResponseDto;
import com.bookcatalog.bookcatalogv2.dto.CategoryQueryDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.exceptions.BadRequestException;
import com.bookcatalog.bookcatalogv2.repositories.CategoryRepository;
import com.bookcatalog.bookcatalogv2.services.CategoryService;
import com.bookcatalog.utils.PaginationUtil;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class CategoryserviceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Override
	public void createAndUpdateCategory(CategoryCreateUpdateRequestDto dto) {

		Category category = this.categoryRepository.findByCode(dto.getCode().toLowerCase()).orElse(new Category());
		if(category.getCode() == null ) {
			category.setCode(dto.getCode().toLowerCase());
		}
		category.setName(dto.getName());
		category.setDescription(dto.getDescription());
		this.categoryRepository.save(category);
	}

	@Override
	public ResultPageresponseDto<CategoryListResponseDto> findCategoryList(Integer pages, Integer limit, String shortBy, String direction, String categoryName) {
		categoryName = StringUtils.isBlank(categoryName) ? "%":categoryName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), shortBy));
		Pageable page = PageRequest.of(pages, limit,sort);
		Page<Category> categories = this.categoryRepository.findByNameLikeIgnoreCase(categoryName, page);
		java.util.List<CategoryListResponseDto> results = categories.stream().map( p -> {
			CategoryListResponseDto category = new CategoryListResponseDto();
			category.setCode(p.getCode());
			category.setName(p.getName());
			category.setDescription(p.getDescription());
			return category;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultResponseDto(results, categories.getTotalElements(), categories.getTotalPages());
	}

	@Override
	public List<Category> findCategories(List<String> categoryCodeList) {
		List<Category> categories = this.categoryRepository.findByCodeIn(categoryCodeList);
		if(categories.isEmpty()) throw new BadRequestException("category cant be null");
		return categories;
	}

	@Override
	public List<CategoryListResponseDto> constructDto(List<Category> categories) {
		return categories.stream().map( c -> {
			CategoryListResponseDto category = new CategoryListResponseDto();
			category.setCode(c.getCode());
			category.setName(c.getName());
			category.setDescription(c.getDescription());
			return category;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Long, List<String>> findCategoryMap(List<Long> bookIdList) {

		java.util.List<CategoryQueryDto> queryList = this.categoryRepository.findCategoryByBookList(bookIdList);
		Map<Long, java.util.List<String>> categoryMaps = new HashMap<Long, java.util.List<String>>();
		java.util.List<String> categoryCodeList = null;
		for (CategoryQueryDto q : queryList) {
			if(!categoryMaps.containsKey(q.getBookId())){
				categoryCodeList = new ArrayList<>();
			}
			else {
				categoryCodeList = categoryMaps.get(q.getBookId());
			}
			categoryCodeList.add(q.getCategoryCode());
			categoryMaps.put(q.getBookId(), categoryCodeList);
		}
		return categoryMaps;
	}

}
