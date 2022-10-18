package com.bookcatalog.bookcatalogv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication @EnableAspectJAutoProxy
public class BookCatalogV2Application {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogV2Application.class, args);
	}

}
