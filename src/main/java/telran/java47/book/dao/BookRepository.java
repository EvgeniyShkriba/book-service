package telran.java47.book.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import telran.java47.book.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, String> {
	List<Book> findBooksByAuthors_Name(String authorName);

	List<Book> findBooksByPublisher_PublisherNameIgnoreCase(String publisherName);

	List<Book> deleteBooksByAuthors_Name(String authorName);
}
