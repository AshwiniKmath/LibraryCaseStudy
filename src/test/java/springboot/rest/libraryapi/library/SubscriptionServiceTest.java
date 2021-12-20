package springboot.rest.libraryapi.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;

import java.util.List;
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
import springboot.rest.libraryapi.library.model.Subscription;
import springboot.rest.libraryapi.library.repository.SubscriptionRepository;
import springboot.rest.libraryapi.library.service.SubscriptionService;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionServiceTest {
	
	@InjectMocks
	private SubscriptionService subscriptionService;
	
	@Mock
	SubscriptionRepository subscriptionRepository;
	
	@Mock
	LibraryController controller;
	
	private Subscription subscription1, subscription2;
	MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		subscription1 = new Subscription();
		subscription1.setSubscription_id(1);
		subscription1.setSubscriber_name("Peter");
		
		subscription2 = new Subscription();
		subscription2.setSubscription_id(2);
		subscription2.setSubscriber_name("John");
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
