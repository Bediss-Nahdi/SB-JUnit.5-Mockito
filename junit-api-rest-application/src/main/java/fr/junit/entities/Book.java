package fr.junit.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data 
@AllArgsConstructor @NoArgsConstructor
@Table(name = "book_record")
@Builder
public class Book {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long idBook;
	
	@NonNull
	private String name;
	
	@NonNull
	private String summary;
	
	private int rating;
	
	
	
	

}
