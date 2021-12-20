package springboot.rest.libraryapi.library.model;

import java.sql.Date;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Subscription {
	
	@Id
	private Integer subscription_id;
	private String subscriber_name;
	private ZonedDateTime date_subscribed;
	private Date date_returned;
	
	@OneToOne
	Book book;
	
	public Subscription(String subscriber_name2, ZonedDateTime date_subscribed2, Date date_returned2,
			Integer subscription_id2, Integer book_id) {
		// TODO Auto-generated constructor stub
	}
	public Subscription() {
		// TODO Auto-generated constructor stub
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getSubscription_id() {
		return subscription_id;
	}
	public void setSubscription_id(Integer subscription_id) {
		this.subscription_id = subscription_id;
	}
	public String getSubscriber_name() {
		return subscriber_name;
	}
	public void setSubscriber_name(String subscriber_name) {
		this.subscriber_name = subscriber_name;
	}
	public ZonedDateTime getDate_subscribed() {
		return date_subscribed;
	}
	public void setDate_subscribed(ZonedDateTime zonedDateTime) {
		this.date_subscribed = zonedDateTime;
	}
	public Date getDate_returned() {
		return date_returned;
	}
	public void setDate_returned(Date date_returned) {
		this.date_returned = date_returned;
	}
	
}
