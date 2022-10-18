package com.bookcatalog.bookcatalogv2.repositories.repositoryimpl;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import com.bookcatalog.bookcatalogv2.domain.Book;
// import com.bookcatalog.bookcatalogv2.repositories.BookRepository;

import lombok.Data;

@Data
public class BookRepositoryImpl {

	// private Map<Long, Book> bookMap = new HashMap<Long, Book>();

	// @Override
	// public Book findBookById(Long id) {
	// 	return bookMap.get(id);
	// }

	// @Override
	// public List<Book> findAllBook() {
	// 	return new ArrayList<Book>(this.bookMap.values());
	// }

	// @Override
	// public void save(Book book) {
	// 	book.setId((long)this.bookMap.size() +1);
	// 	bookMap.put(book.getId(), book);
	// }

	// @Override
	// public void update(Book book) {
	// 	this.bookMap.put(book.getId(), book);
	// }

	// @Override
	// public void delete(Long id) {
	// 	this.bookMap.remove(id);

	// }


}
