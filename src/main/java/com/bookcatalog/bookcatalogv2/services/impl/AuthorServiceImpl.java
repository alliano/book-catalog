package com.bookcatalog.bookcatalogv2.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.domain.Address;
import com.bookcatalog.bookcatalogv2.domain.Author;
import com.bookcatalog.bookcatalogv2.dto.AuthorCreateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorQueryDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorResponseDto;
import com.bookcatalog.bookcatalogv2.dto.AuthorUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.exceptions.BadRequestException;
import com.bookcatalog.bookcatalogv2.exceptions.ResourceNotfoundException;
import com.bookcatalog.bookcatalogv2.repositories.AuthorRepository;
import com.bookcatalog.bookcatalogv2.services.AuthorService;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	@Override
	public AuthorResponseDto findAuthorBySecureId(String secureId) {
		AuthorResponseDto dto = new AuthorResponseDto();
		Author author = this.authorRepository.findBySecureId(secureId).orElseThrow( () -> new ResourceNotfoundException("invalid.secureId"));
		dto.setName(author.getName());
		dto.setBrithDate(author.getBirthDate().toEpochDay());
		System.out.println("\n\n");
		List<Address> addres = author.getAddresses();
		addres.forEach(a -> {
			System.out.println("CITY NAME : ".concat(a.getCictyName()));
			System.out.println("STREET NAME : ".concat(a.getStreetName()));
			System.out.println("ZIP CODE : ".concat(a.getZipCode()));
			System.out.println("AUTHOR NAME : ".concat(a.getAuthor().getName()));
		});
		System.out.println("\n\n");
		return dto;
	}

	@Override
	public List<AuthorResponseDto> findByNameLike(String name) {
		// List<Author> author = this.authorRepository.findByNameLike(name);
		// AuthorResponseDto dto = new AuthorResponseDto();

		return null;
	}

	@Override
	public void createNewAuthor(java.util.List<AuthorCreateRequestDto> dtos) {
		java.util.List<com.bookcatalog.bookcatalogv2.domain.Author> authors = dtos.stream().map( dto -> {
			Author author = new Author();
			author.setName(dto.getAuthorName());
			author.setBirthDate(LocalDate.ofEpochDay(dto.getBrithDate()));
			author.setAddresses(dto.getAddresses().stream().map( a -> {
				Address address = new Address();
				address.setCictyName(a.getCityName());
				address.setStreetName(a.getStreetName());
				address.setZipCode(a.getZipCode());
				address.setAuthor(author);
				return address;
			}).collect(Collectors.toList()));
			return author;
		}).collect(Collectors.toList());
		this.authorRepository.saveAll(authors);
	}
	@Override
	public void updateAuthor(String authorSecureId, AuthorUpdateRequestDto dto) {
		Author author = this.authorRepository.findBySecureId(authorSecureId)
		.orElseThrow(() -> new ResourceNotfoundException("invalid.secureId"));
		Map<Long, Address> addressMap = author.getAddresses().stream().map( a -> { return a;})
		.collect(Collectors.toMap(Address::getId, Function.identity()));
		java.util.List<Address> addresses = dto.getAddresses().stream().map( a -> {
			Address address = addressMap.get(a.getAddressId());
			address.setCictyName(a.getCityName());
			address.setStreetName(a.getStreetName());
			address.setZipCode(a.getZipCode());
			return address;
		}).collect(Collectors.toList());
		author.setAddresses(addresses);
		author.setName(dto.getAuthorName() == null ? author.getName() : dto.getAuthorName());
		author.setBirthDate(dto.getBirthDate() == null ? author.getBirthDate() : LocalDate.ofEpochDay(dto.getBirthDate()));
		this.authorRepository.save(author);
	}

	@Override
	public void deleteAuthor(String secureId) {
		Author author = this.authorRepository.findBySecureId(secureId).orElseThrow(() -> new ResourceNotfoundException("invalid.secureId"));
		this.authorRepository.delete(author);
	}

	@Override
	public List<Author> findAuthors(List<String> authorIdList) {
		List<Author> authors = this.authorRepository.findBySecureIdIn(authorIdList);
		if(authors.isEmpty()) throw new BadRequestException("author can't be empty");
		return authors;
	}

	@Override
	public List<AuthorResponseDto> authorConstrucDto(List<Author> authors) {
		return authors.stream().map( a -> {
			AuthorResponseDto author = new AuthorResponseDto();
			author.setName(a.getName());
			author.setBrithDate(a.getBirthDate().toEpochDay());
			return author;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<Long, List<String>> findAuthorMaps(List<Long> bookIdList) {
		List<AuthorQueryDto> queryList = this.authorRepository.findAuthorsByBookIdList(bookIdList);
		Map<Long, List<java.lang.String>> authorsMap = new HashMap<>();
		List<String> authorList = null;
		for( AuthorQueryDto q : queryList ) {
			if(!authorsMap.containsKey(q.getBookId())) {
				authorList = new ArrayList<String>();
			}
			else {
				authorList = authorsMap.get(q.getBookId());
			}
			authorList.add(q.getAuthorName());
			authorsMap.put(q.getBookId(), authorList);
		}
		return authorsMap;
	}

}
