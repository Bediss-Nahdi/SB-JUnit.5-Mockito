package fr.junit.BookControllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.assertj.core.util.Arrays;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import fr.junit.controllers.BookController;
import fr.junit.entities.Book;
import fr.junit.respositories.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookController bookController;

	Book RECORD_1 = new Book(1L, "Harry Potter et la pière Philosophale", "Sommaire Générale", 5);
	Book RECORD_2 = new Book(2L, "Harry Potter et la chambre des secrets", "Sommaire Générale", 4);
	Book RECORD_3 = new Book(3L, "Harry Potter et le prisionnier d'Azkaban", "Sommaire Générale", 5);

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}
	
	
	// NE FONCTIONNE PAS
	@Test
	public void getAllRecords_succes() throws Exception{
		List<Book> records = new ArrayList<>();
		records.add(RECORD_1);
		records.add(RECORD_3);
		records.add(RECORD_2);
		//bookRepository.saveAll(records);
		
		
		Mockito.when(bookRepository.findAll()).thenReturn(records);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/book")
				.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						//.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is("") ))
						.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$", hasSize(3)))
						.andExpect(jsonPath("$[2].name", is("Harry Potter et le prisionnier d'Azkaban")));
	}

	private Object hasSize(int i) {
		
		return i;
	}
	
	
	@Test
	public void getBookById_success() throws Exception {
		Mockito.when(bookRepository.findById(RECORD_1.getIdBook())).thenReturn(Optional.of(RECORD_1));
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/book/1")
				.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						//.andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.is("") ))
						.andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$", notNullValue()))
						.andExpect(jsonPath("$.name", is("Harry Potter et la pière Philosophale")));
	}
	
	
	
}
