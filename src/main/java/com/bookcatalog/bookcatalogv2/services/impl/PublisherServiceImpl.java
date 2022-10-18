package com.bookcatalog.bookcatalogv2.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.domain.Publisher;
import com.bookcatalog.bookcatalogv2.dto.PublisherCreaterequestDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherListresponseDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherResponseDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;
import com.bookcatalog.bookcatalogv2.exceptions.BadRequestException;
import com.bookcatalog.bookcatalogv2.repositories.PublisherRepository;
import com.bookcatalog.bookcatalogv2.services.PublisherService;
import com.bookcatalog.utils.PaginationUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;

	@Override
	public void createPublisher(PublisherCreaterequestDto dto) {
		Publisher publisher = new Publisher();
		publisher.setName(dto.getPublisherName());
		publisher.setAddress(dto.getAddress());
		publisher.setCompanyName(dto.getCompanyName());
		this.publisherRepository.save(publisher);
	}
	@Override
	public void updatePublisher(String secureId, PublisherUpdateRequestDto dto) {
		Publisher publisher = this.publisherRepository.findBySecureId(secureId).orElseThrow(() -> new BadRequestException("invalid.secureId"));
		publisher.setAddress(dto.getAddress() == null || dto.getAddress().isBlank() ? publisher.getAddress() : dto.getAddress());
		publisher.setCompanyName(dto.getCompanyName() == null || dto.getCompanyName().isBlank() ? publisher.getCompanyName() : dto.getCompanyName());
		publisher.setName(dto.getPublisherName() == null || dto.getPublisherName().isBlank() ? publisher.getName() : dto.getPublisherName());
		this.publisherRepository.save(publisher);
	}
	@Override
	public ResultPageresponseDto<PublisherListresponseDto> findPublisherList(Integer page, Integer limit, String sortBy, String direction, String publisherName) {
		publisherName = StringUtils.isAllBlank(publisherName) ?"%":publisherName+"%";
		Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page<Publisher> pageResults = this.publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
		List<PublisherListresponseDto> result = pageResults.stream().map( p -> {
			PublisherListresponseDto dto = new PublisherListresponseDto();
			dto.setComapnyName(p.getCompanyName());
			dto.setPublisherId(p.getSecureId());
			dto.setPublisherName(p.getName());
			return dto;
		}).collect(Collectors.toList());
 		return PaginationUtil.createResultResponseDto(result, pageResults.getTotalElements(), pageResults.getTotalPages());
	}
	@Override
	public Publisher findPublisher(String publisherId) {
		return this.publisherRepository.findBySecureId(publisherId).orElseThrow(() -> new BadRequestException("invalid.secureId"));
	}
	@Override
	public PublisherResponseDto publisherConstrucDto(Publisher publisher) {
		PublisherResponseDto dto = new PublisherResponseDto();
		dto.setPublisherId(publisher.getSecureId());
		dto.setPublisherName(publisher.getName());
		return dto;
	}

}
