package com.bookcatalog.utils;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;

public class PaginationUtil {

	public static Sort.Direction getSortBy(String sortBy) {
		if(sortBy.equalsIgnoreCase("asc")) return Sort.Direction.ASC;
		else
		return Sort.Direction.DESC;
	}

	public static <T> ResultPageresponseDto<T> createResultResponseDto(List<T> dtos, Long totalElements, int pages){

		ResultPageresponseDto<T> result = new ResultPageresponseDto<T>();
		result.setElements(totalElements);
		result.setPage(pages);
		result.setResult(dtos);
		return result;

	}
}
