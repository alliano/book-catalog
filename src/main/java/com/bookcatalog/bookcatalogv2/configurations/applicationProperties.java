package com.bookcatalog.bookcatalogv2.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class applicationProperties {

	private String timeZone;

	private String currency;

	private String welcomeTxt;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getWelcomeTxt() {
		return welcomeTxt;
	}

	public void setWelcomeTxt(String welcomeTxt) {
		this.welcomeTxt = welcomeTxt;
	}
}
