package springboot.rest.libraryapi.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springboot.rest.libraryapi.library.controller.LibraryController;
import springboot.rest.libraryapi.library.model.Book;
import springboot.rest.libraryapi.library.repository.BookRepository;
import springboot.rest.libraryapi.library.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@InjectMocks
	BookService bookService;
	
	@Mock
	BookRepository bookRepo;
	
	@Mock
	LibraryController controller;
	
	private Book book1, book2;
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		
		book1 = new Book();
		book1.setBook_id(1);
		book1.setBook_name("History Of Amazon Valley");
		book1.setAuthor("Ross Suarez");
		book1.setAvialable_copies(0);
		book1.setTotal_copies(2);
		
		book2 = new Book();
		book2.setBook_id(2);
		book2.setBook_name("Language of Fundamentals");
		book2.setAuthor("H S Parkmay");
		book2.setAvialable_copies(2);
		book2.setTotal_copies(10);
		
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void getBooks() {
		Mockito.when(bookRepo.findById(Mockito.eq(1))).thenReturn(Optional.ofNullable(book1));
		Book booksReturned = bookService.getBook(1);
		assertEquals("1",booksReturned.getBook_id());
		assertEquals("History Of Amazon Valley",booksReturned.getBook_name());
		Mockito.verify(bookRepo,times(1)).getById(Mockito.eq(1));
	}
	
	@Test
	public void getBooksShouldThrowException() {
		Mockito.when(bookRepo.findById(Mockito.eq(10))).thenReturn(Optional.empty());
		Throwable exception = assertThrows(NullPointerException.class,()-> bookService.getBook(10));
		assertEquals("Book with ID 10 not found",exception.getMessage());
	}

}
