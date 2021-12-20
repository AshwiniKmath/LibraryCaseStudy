package springboot.rest.libraryapi.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import springboot.rest.libraryapi.library.controller.LibraryController;
import springboot.rest.libraryapi.library.model.Book;
import springboot.rest.libraryapi.library.model.Subscription;
import springboot.rest.libraryapi.library.repository.BookRepository;
import springboot.rest.libraryapi.library.repository.SubscriptionRepository;
import springboot.rest.libraryapi.library.service.BookService;
import springboot.rest.libraryapi.library.service.SubscriptionService;

@SpringBootTest
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

	@InjectMocks
	BookService bookService;
	
	@Mock
	BookRepository bookRepo;
	
	@Mock
	LibraryController controller;
	
	@InjectMocks
	private SubscriptionService subscriptionService;
	
	@Mock
	SubscriptionRepository subscriptionRepository;
	
	private Subscription subscription1, subscription2;
	
	private Book book1, book2;
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		
		subscription1 = new Subscription();
		subscription1.setSubscription_id(1);
		subscription1.setSubscriber_name("Peter");
		
		subscription2 = new Subscription();
		subscription2.setSubscription_id(2);
		subscription2.setSubscriber_name("John");
		
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
	
	@Test
	public void getAllBySubscriptionNameShouldReturnSubscriber(){
			Mockito.when(subscriptionRepository.findById(Mockito.eq(1))).thenReturn(Optional.ofNullable(subscription1));
			List<Subscription> subscriptionReturned = subscriptionService.getAllBySubscriptionName("Peter");
			assertEquals(subscription1.getSubscription_id(),subscriptionReturned.get(0).getSubscription_id());
			assertEquals(subscription1.getSubscriber_name(),subscriptionReturned.get(0).getSubscriber_name());
			Mockito.verify(subscriptionRepository,times(1)).findAllBySubscriptionName(Mockito.eq("Peter"));
	}
	
	@Test
	public void getAllBySubscriptionNameShouldThrowException() {
		Mockito.when(subscriptionRepository.findById(Mockito.eq(10))).thenReturn(Optional.empty());
		Throwable exception = assertThrows(NullPointerException.class,()-> subscriptionService.getAllBySubscriptionName("Peter"));
		assertEquals("Subscription with ID 10 not found",exception.getMessage());
	}
	
	@Test
	public void borrowBookShouldGetBook() {
		Mockito.when(subscriptionService.borrowBook(subscription1)).thenReturn(subscription1);
		Subscription borrowed = subscriptionService.borrowBook(subscription1);
		assertEquals("1",borrowed.getSubscription_id());
		assertEquals("Peter",borrowed.getSubscriber_name());
		Mockito.verify(subscriptionService,times(1)).borrowBook(borrowed);
	}
	
	@Test
	public void borrowBookShouldThrowException() {
		Mockito.when(subscriptionRepository.findById(Mockito.eq(10))).thenReturn(Optional.empty());
		Throwable exception = assertThrows(NullPointerException.class,()-> subscriptionService.borrowBook(subscription1));
		assertEquals("Subscription not allowed",exception.getMessage());
	}
	
}