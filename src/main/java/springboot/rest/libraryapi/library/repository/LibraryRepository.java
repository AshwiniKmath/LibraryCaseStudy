package springboot.rest.libraryapi.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.rest.libraryapi.library.model.Book;

@Repository
public interface LibraryRepository extends JpaRepository<Book, Integer>{

}
