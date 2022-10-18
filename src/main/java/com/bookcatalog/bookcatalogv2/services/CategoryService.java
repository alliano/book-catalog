package com.bookcatalog.bookcatalogv2.services;

import java.util.List;
import java.util.Map;

import com.bookcatalog.bookcatalogv2.domain.Category;
import com.bookcatalog.bookcatalogv2.dto.*;

public interface CategoryService {

	public void createAndUpdateCategory(CategoryCreateUpdateRequestDto dto);

	public ResultPageresponseDto<CategoryListResponseDto> findCategoryList(Integer pages, Integer limit, String shortBy, String direction, String categoryName);

	public List<Category> findCategories(List<String> categoryCodeList);

	public List<CategoryListResponseDto> constructDto(List<Category> categories);

	public Map<Long, java.util.List<String>> findCategoryMap(List<Long> bookIdList);
}
