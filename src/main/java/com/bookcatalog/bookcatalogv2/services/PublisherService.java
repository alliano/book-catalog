package com.bookcatalog.bookcatalogv2.services;

import com.bookcatalog.bookcatalogv2.domain.Publisher;
import com.bookcatalog.bookcatalogv2.dto.PublisherCreaterequestDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherListresponseDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherResponseDto;
import com.bookcatalog.bookcatalogv2.dto.PublisherUpdateRequestDto;
import com.bookcatalog.bookcatalogv2.dto.ResultPageresponseDto;

public interface PublisherService {

	public void createPublisher(PublisherCreaterequestDto dto);

	public void updatePublisher(String publisherId, PublisherUpdateRequestDto dto);

	public ResultPageresponseDto<PublisherListresponseDto> findPublisherList(Integer pages, Integer limit, String sortBy, String direction, String publisherName);

	public Publisher findPublisher(java.lang.String publisherId);

	public PublisherResponseDto publisherConstrucDto(Publisher publisher);
}
