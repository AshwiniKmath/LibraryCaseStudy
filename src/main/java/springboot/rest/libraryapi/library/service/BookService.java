package springboot.rest.libraryapi.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springboot.rest.libraryapi.library.model.Book;
import springboot.rest.libraryapi.library.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	BookRepository bookRepository;
	
	public List<Book> fetchAllBooks() {
		return bookRepository.findAll();
	}

	public Book getBook(Integer book_id) {
		// TODO Auto-generated method stub
		Optional<Book> bookTosave = bookRepository.findById(book_id);
		return bookTosave.isPresent() ? bookTosave.get() : null;
	}

	public Book updateBook(Integer book_id, Book book) {
		Optional<Book> bookTosave = bookRepository.findById(book_id);
		Book book1 = bookTosave.get();
		return this.bookRepository.save(book1);
	}
}
