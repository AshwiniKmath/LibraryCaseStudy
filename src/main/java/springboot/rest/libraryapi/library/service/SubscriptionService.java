package springboot.rest.libraryapi.library.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import springboot.rest.libraryapi.library.exception.LibraryExceptionHandler;
import springboot.rest.libraryapi.library.model.Book;
import springboot.rest.libraryapi.library.model.Subscription;
import springboot.rest.libraryapi.library.repository.BookRepository;
import springboot.rest.libraryapi.library.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
	
	

	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookService bookService;

		
		
	public List<Subscription> getAllBySubscriptionName(@Param("subscriber_name") String subscriberName){
			return subscriptionRepository.findAllBySubscriptionName(subscriberName);
	}

	@Transactional
	public Subscription borrowBook(@Valid Subscription subcription){
		Book book = bookService.getBook(subcription.getBook().getBook_id());
		if(book.getAvialable_copies()==0) {
			throw new NullPointerException("Book is not availlable"); 
			//throw new LibraryExceptionHandler(String.format("Book is not availlable", book.getBook_name()));
		}
		book.setAvialable_copies(book.getAvialable_copies()-1);
		bookService.updateBook(book.getBook_id(),book);
		Subscription subscriptionToCreate = new Subscription();
		subscriptionToCreate.setBook(book);
		subscriptionToCreate.setDate_subscribed(ZonedDateTime.now());
		subscriptionToCreate.setSubscriber_name(subcription.getSubscriber_name());
		subscriptionToCreate.setSubscription_id(subcription.getSubscription_id());
		return this.subscriptionRepository.save(subscriptionToCreate);
	}

	public boolean isBookAvailable(@Valid Book book) {
		Integer bookAvailable = book.getAvialable_copies();
		if(bookAvailable>0)
			return true;
		else
			return false;
	}

	
}
