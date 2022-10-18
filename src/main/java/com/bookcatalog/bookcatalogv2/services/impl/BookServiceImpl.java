package com.bookcatalog.bookcatalogv2.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.domain.Author;
import com.bookcatalog.bookcatalogv2.domain.Book;
import com.bookcatalog.bookcatalogv2.domain.Category;
import com.bookcatalog.bookcatalogv2.domain.Publisher;
import com.bookcatalog.bookcatalogv2.dto.BookCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.BookDetailResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookListResponseDto;
import com.bookcatalog.bookcatalogv2.dto.BookQueryDto;
import com.bookcatalog.bookcatalogv2.dto.BookUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.exceptions.BadRequestException;
import com.bookcatalog.bookcatalogv2.exceptions.ResourceNotfoundException;
import com.bookcatalog.bookcatalogv2.repositories.BookRepository;
import com.bookcatalog.bookcatalogv2.services.AuthorService;
import com.bookcatalog.bookcatalogv2.services.BookService;
import com.bookcatalog.bookcatalogv2.services.CategoryService;
import com.bookcatalog.bookcatalogv2.services.PublisherService;
import com.bookcatalog.utils.PaginationUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor @Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	private final AuthorService authorService;

	private final CategoryService categoryService;

	private final PublisherService publisherService;

	@Override
	public BookDetailResponseDto findDetailBySecureId(String secureId) {
		Book book = bookRepository.findBySecureId(secureId).orElseThrow( () -> new BadRequestException("invalid.secureId"));
		BookDetailResponseDto dto = new BookDetailResponseDto();
		dto.setBookId(book.getSecureId());
		dto.setCategories(this.categoryService.constructDto(book.getCategories()));
		dto.setAuthors(this.authorService.authorConstrucDto(book.getAuthors()));
		dto.setBookTitle(book.getTitle());
		dto.setPublisher(this.publisherService.publisherConstrucDto(book.getPublisher()));
		dto.setBookDescriptions(book.getDescriptions());
		return dto;
	}

	@Override
	public List<BookDetailResponseDto> findBookList() {
		List<Book> books = this.bookRepository.findAll();
		return books.stream().map( book -> {
			BookDetailResponseDto dto = new BookDetailResponseDto();
			dto.setBookId(book.getSecureId());
			dto.setAuthors(this.authorService.authorConstrucDto(book.getAuthors()));
			dto.setBookTitle(book.getTitle());
			dto.setPublisher(this.publisherService.publisherConstrucDto(book.getPublisher()));
			dto.setCategories(this.categoryService.constructDto(book.getCategories()));
			dto.setBookDescriptions(book.getDescriptions());
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public void createNewBook(BookCreateRequestDto dto) {
		java.util.List<Author> authors = this.authorService.findAuthors(dto.getAuthorIdList());
		List<Category> categories = this.categoryService.findCategories(dto.getCategoryList());
		Publisher publisher = this.publisherService.findPublisher(dto.getPublisherId());
		Book book = new Book();
		book.setAuthors(authors);
		book.setCategories(categories);
		book.setPublisher(publisher);
		book.setTitle(dto.getBookTitle());
		book.setDescriptions(dto.getDescriptions());
		this.bookRepository.save(book);
	}

	@Override
	public void updateBook(String secureId, BookUpdateRequestDto dto) {
		Book book = bookRepository.findBySecureId(secureId).orElseThrow( () -> new ResourceNotfoundException("invalid.secureId"));
		book.setDescriptions(dto.getDescription());
		book.setTitle(dto.getBookTitle());
		this.bookRepository.save(book);
	}
	@Override
	public void deleteBook(Long id) {
		this.bookRepository.deleteById(id);
	}
	@Override
	public ResultPageresponseDto<BookListResponseDto> findBookList(Integer page, Integer limit, String sortBy, String direction, String publisherName, String authorName, String bookTitle) {
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(page, limit, sort );
		Page<BookQueryDto> pageResults = this.bookRepository.findBookList(bookTitle, publisherName, authorName, pageable);
		List<Long> idList = pageResults.stream().map( b -> b.getId()).collect(Collectors.toList());
		Map<Long, List<String>> categoryMap = this.categoryService.findCategoryMap(idList);
		Map<Long, List<String>> authorsMap = this.authorService.findAuthorMaps(idList);
		List<BookListResponseDto> dtos = pageResults.stream().map( b -> {
			BookListResponseDto dto = new BookListResponseDto();
			dto.setAuthorNames(authorsMap.get(b.getId()));
			dto.setCatagoryCodes(categoryMap.get(b.getId()));
			dto.setId(b.getBookId());
			dto.setPublisherName(b.getPublisherName());
			dto.setDescription(b.getDescription());
			dto.setTitle(b.getBookTitle());
			return dto;
		}).collect(Collectors.toList());
		return PaginationUtil.createResultResponseDto(dtos, pageResults.getTotalElements(), pageResults.getTotalPages());
	}

}
