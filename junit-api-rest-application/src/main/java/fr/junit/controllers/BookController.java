package fr.junit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.junit.entities.Book;
import fr.junit.respositories.BookRepository;

@RestController
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping
	public List<Book> getAllBookRecords() {
		return bookRepository.findAll();
	}

	@GetMapping(value = "/{bookId}")
	public Book getAllBookRecords(@PathVariable(value = "bookId") Long id) {
		return bookRepository.findById(id).get();
	}

	@PostMapping
	public Book createBook(@RequestBody @Validated Book bookReccord) {
		return bookRepository.save(bookReccord);
	}

	@PutMapping

	public Book updateBook(@RequestBody @Validated Book bookReccord) throws Exception {
		if (bookReccord == null || bookReccord.getIdBook() == null) {
			throw new Exception("Nous n'avons pas trouvé de livre");
		}
		Optional<Book> bookOptional = bookRepository.findById(bookReccord.getIdBook());
		if (!bookOptional.isPresent()) {
			throw new Exception("Le livre avec l'id : " + bookReccord.getIdBook() + "n'existe pas ! ");
		}

		Book existingBookRecord = bookOptional.get();
		existingBookRecord.setName(bookReccord.getName());
		existingBookRecord.setRating(bookReccord.getRating());
		existingBookRecord.setSummary(bookReccord.getSummary());

		return bookRepository.save(existingBookRecord);
	}

	@DeleteMapping(value = "/{bookId}")
	public void deleteBookById(@PathVariable(value = "bookId") Long id) {
		bookRepository.deleteById(null);
	}

}
