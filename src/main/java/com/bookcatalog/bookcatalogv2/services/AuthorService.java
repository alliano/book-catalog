package com.bookcatalog.bookcatalogv2.services;

import java.util.List;

import com.bookcatalog.bookcatalogv2.domain.Author;
import com.bookcatalog.bookcatalogv2.dto.AuthorCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorResponseDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorUpdateRequestDto;

public interface AuthorService {

	public AuthorResponseDto findAuthorBySecureId(String secureId);

	public List<AuthorResponseDto> findByNameLike(String name);

	public void createNewAuthor(java.util.List<AuthorCreateRequestDto> author);

	public void updateAuthor(String authorSecureId, AuthorUpdateRequestDto dto);

	public void deleteAuthor(String secureId);

	public List<Author> findAuthors(List<String> authorIdList);

	public List<AuthorResponseDto> authorConstrucDto(List<com.bookcatalog.bookcatalogv2.domain.Author> authors);

	public java.util.Map<java.lang.Long, java.util.List<java.lang.String>> findAuthorMaps(java.util.List<java.lang.Long> bookIdList);


}
