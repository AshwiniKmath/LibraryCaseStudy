package springboot.rest.libraryapi.library.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private Integer book_id;
	
	private String book_name;
	
	private String author;
	
	private Integer avialable_copies;
	
	private Integer total_copies;
	
	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAvialable_copies() {
		return avialable_copies;
	}

	public void setAvialable_copies(Integer avialable_copies) {
		this.avialable_copies = avialable_copies;
	}

	public Integer getTotal_copies() {
		return total_copies;
	}

	public void setTotal_copies(Integer total_copies) {
		this.total_copies = total_copies;
	}

}