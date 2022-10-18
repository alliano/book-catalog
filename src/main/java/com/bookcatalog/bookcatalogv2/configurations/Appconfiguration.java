package com.bookcatalog.bookcatalogv2.configurations;
// package com.bookcatalog.bookcatalogv2.configuration;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.bookcatalog.bookcatalogv2.domain.Author;
// import com.bookcatalog.bookcatalogv2.domain.Book;
// import com.bookcatalog.bookcatalogv2.repositories.BookRepository;
// import com.bookcatalog.bookcatalogv2.repositories.repositoryimpl.BookRepositoryImpl;

// @Configuration
// public class Appconfiguration {


// 	@Bean(name = "author")
// 	public Author author(){
// 		return new Author(1L, "alliano", null,false);
// 	}

// 	@Bean(name = "book1")
// 	public Book book1(Author author){
// 		Book book = new Book();
// 		book.setId(1L);
// 		// book.setAuthor(author);
// 		book.setDescriptions("the reason why you must be hackers");
// 		book.setTitle("the way to be hacker");
// 		return book;
// 	}
// 	@Bean(name = "book2")
// 	public Book book2(Author author){
// 		Book book = new Book();
// 		book.setId(2L);
// 		// book.setAuthor(author);
// 		book.setDescriptions("tutorials to avoid pain");
// 		book.setTitle("Pain is cycle of live");
// 		return book;
// 	}
// 	@Bean(name = "book3")
// 	public Book book3(Author author){
// 		Book book = new Book();
// 		book.setId(3L);
// 		// book.setAuthor(author);
// 		book.setDescriptions("the steps to become good man");
// 		book.setTitle("the sort step to be good man");
// 		return book;
// 	}

// 	@Bean(name = "bookRepository")
// 	public BookRepository bookRepository(Book book1, Book book2,Book book3){
// 		Map<Long, Book> bookMap = new HashMap<Long,Book>();
// 		bookMap.put(book1.getId(), book1);
// 		bookMap.put(book2.getId(), book2);
// 		bookMap.put(book3.getId(), book3);

// 		BookRepositoryImpl bookRepositoryImpl = new BookRepositoryImpl();
// 		bookRepositoryImpl.setBookMap(bookMap);
// 		return bookRepositoryImpl;
// 	}
// }
