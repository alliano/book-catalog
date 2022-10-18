package com.bookcatalog.bookcatalogv2.services;

import java.util.List;

import com.bookcatalog.bookcatalogv2.dto.BookCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.BookDetailResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookListResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;

public interface BookService {

	public BookDetailResponseDto findDetailBySecureId(String secureId);

	public List<BookDetailResponseDto> findBookList();

	public void createNewBook(BookCreateRequestDto book);

	public void updateBook(String secureId, BookUpdateRequestDto book);

	public void deleteBook(Long id);

	public ResultPageresponseDto<BookListResponseDto> findBookList(Integer page, Integer limit, String sortBy, String direction, String publisherName, String authorName, String bookTitle);
}
