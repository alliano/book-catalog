package com.bookcatalog.bookcatalogv2.services.impl;

import java.util.TimeZone;

import org.springframework.stereotype.Service;

import com.bookcatalog.bookcatalogv2.configurations.applicationProperties;
import com.bookcatalog.bookcatalogv2.services.GrettingService;

@Service
public class GrettingServiceImpl implements GrettingService {


	private applicationProperties applicationProperties;

	public GrettingServiceImpl(applicationProperties applicationProperties) {

		this.applicationProperties = applicationProperties;
	}

	@Override
	public String sayHello() {
		TimeZone timeZone = TimeZone.getTimeZone(this.applicationProperties.getTimeZone());
		return this.applicationProperties.getWelcomeTxt() +" "+ "our time zone is : " + timeZone.getDisplayName()+" "+ "our currency is : "+ this.applicationProperties.getCurrency();
	}

}
