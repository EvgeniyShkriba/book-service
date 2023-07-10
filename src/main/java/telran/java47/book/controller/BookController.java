package telran.java47.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.model.Publisher;
import telran.java47.book.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController implements BookService {
	final BookService bookService;

	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

	@GetMapping("/book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}

	@DeleteMapping("/book/{isbn}")
	public BookDto removeBookByIsbn(@PathVariable String isbn) {
		return bookService.removeBookByIsbn(isbn);
	}

	@PutMapping("/book/{isbn}/title/{title}")
	public BookDto updateBookByTitle(@PathVariable String isbn, @PathVariable String title) {
		return bookService.updateBookByTitle(isbn, title);
	}

	@GetMapping("/books/author/{author}")
	public List<BookDto> findBooksByAuthor(@PathVariable String author) {
		return bookService.findBooksByAuthor(author);
	}

	@GetMapping("/books/publisher/{publisherName}")
	public List<BookDto> findBooksByPublisherName(@PathVariable String publisherName) {
		return bookService.findBooksByPublisherName(publisherName);
	}

	@GetMapping("/authors/book/{isbn}")
	public List<AuthorDto> findAuthorsByBook(@PathVariable String isbn) {
		return bookService.findAuthorsByBook(isbn);
	}

	@DeleteMapping("/author/{name}")
	public AuthorDto removeAuthorByName(@PathVariable String name) {
		return bookService.removeAuthorByName(name);
	}

@GetMapping("/publishers/author/{name}")
	public Iterable<String> findPublishersByAuthorName(@PathVariable String name) {
		return bookService.findPublishersByAuthorName(name);
	}

}
