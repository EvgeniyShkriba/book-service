package telran.java47.book.service;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java47.book.dao.AuthorRepository;
import telran.java47.book.dao.BookRepository;
import telran.java47.book.dao.PublisherRepository;
import telran.java47.book.dto.AuthorDto;
import telran.java47.book.dto.BookDto;
import telran.java47.book.model.Author;
import telran.java47.book.model.Book;
import telran.java47.book.model.Publisher;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	final BookRepository bookRepository;
	final AuthorRepository authorRepository;
	final PublisherRepository publisherRepository;
	final ModelMapper modelMapper;

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		// Publisher
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		// Authors
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto removeBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public BookDto updateBookByTitle(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
		book.setTitle(title);

		return modelMapper.map(book, BookDto.class);
	}

	 @Override
	    public List<BookDto> findBooksByAuthor(String author) {
	        return bookRepository.findBooksByAuthors_Name(author).stream()
	                .map(book -> modelMapper.map(book, BookDto.class))
	                .collect(Collectors.toList());
	    }

	 @Override
	 public List<BookDto> findBooksByPublisherName(String publisherName) {
		    List<Book> books = bookRepository.findBooksByPublisher_PublisherNameIgnoreCase(publisherName);
		    return books.stream()
		            .map(book -> modelMapper.map(book, BookDto.class))
		            .collect(Collectors.toList());
		}


	 @Override
	 public List<AuthorDto> findAuthorsByBook(String isbn) {
	     Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
	     Set<Author> authors = book.getAuthors();
	     return authors.stream()
	             .map(author -> modelMapper.map(author, AuthorDto.class))
	             .collect(Collectors.toList());
	 }

	 @Override
	 @Transactional
	 public AuthorDto removeAuthorByName(String name) {
	     Author author = authorRepository.findByName(name);
	     if (author == null) {
	         throw new EntityNotFoundException("Author not found: " + name);
	     }
        bookRepository.deleteBooksByAuthors_Name(name);
	     authorRepository.delete(author);
	     return new AuthorDto(author.getName(), author.getBirthDate());
	 }



	@Override
	public List<Publisher> findPublishersByAuthorName(String name) {
//	    List<Book> books = bookRepository.findBooksByAuthors_Name(name);
//	    return books.stream()
//	            .map(Book::getPublisher)
//	            .collect(Collectors.toList());
		return null;
	}


}
