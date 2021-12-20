package springboot.rest.libraryapi.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springboot.rest.libraryapi.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
