package springboot.rest.libraryapi.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import springboot.rest.libraryapi.library.model.Book;
import springboot.rest.libraryapi.library.model.Subscription;
import springboot.rest.libraryapi.library.service.BookService;
import springboot.rest.libraryapi.library.service.SubscriptionService;

@RestController
@RequestMapping("/library")
public class LibraryController {
	
	@Autowired
	BookService boookService;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		return boookService.fetchAllBooks();
	}
	
	@GetMapping("/subsciptions/{subscriberName}")
	public ResponseEntity<List<Subscription>> getSelectedSubscriber(@RequestParam String subscriberName) {
		List<Subscription> list= new ArrayList<Subscription>();
		if(subscriberName!=null) {
			List<Subscription> sub = subscriptionService.getAllBySubscriptionName(subscriberName);
			list.addAll(sub);	
		}else {
			//list.getAll();
		}
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping("/subsciptions")
	public String borrowBook(@RequestBody @Valid Subscription subscription) {
		subscriptionService.borrowBook(subscription);
		return ("Subciption Created.");
	}

	//Calling microservice
	@GetMapping("/address")
	public String getAddressofSubscriber() {
		String sub = restTemplate.getForObject("http://localhost:8082/employees/address/", String.class);

	        if (sub == null)
	        	return sub; 
	        // throw new NullPointerException(subscriptionID+" not found");
	        else {
	        	return sub;
	        }
		
	}
	
}
