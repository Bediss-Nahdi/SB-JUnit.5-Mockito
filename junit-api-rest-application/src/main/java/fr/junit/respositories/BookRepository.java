package fr.junit.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.junit.entities.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
