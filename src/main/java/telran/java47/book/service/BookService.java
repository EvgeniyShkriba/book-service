package telran.java47.book.service;

import java.util.List;

import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.model.Publisher;

public interface BookService {
	boolean addBook(BookDto bookDto);

	BookDto findBookByIsbn(String isbn);

	BookDto removeBookByIsbn(String isbn);

	BookDto updateBookByTitle(String isbn, String title);

	List<BookDto> findBooksByAuthor(String author);

	List<BookDto> findBooksByPublisherName(String publisherName);

	List<AuthorDto> findAuthorsByBook(String isbn);
	AuthorDto removeAuthorByName(String name);
	
	List<Publisher> findPublishersByAuthorName(String name);
}
