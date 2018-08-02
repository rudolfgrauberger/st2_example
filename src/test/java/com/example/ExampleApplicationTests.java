package com.example;

import com.example.Entities.Author;
import com.example.Entities.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
public class ExampleApplicationTests {

	@Test
	public void testSerializeAuthorWithCircularDependenciesToJson() throws ParseException, JsonProcessingException {
		String testDate = "29-10-2010";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(testDate);

		Author author1 = new Author("NoName Author");
		Book book1 = new Book("My Book", date);
		author1.addBook(book1);


		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(author1);


		assertThat(json).isEqualTo("{\"name\":\"NoName Author\",\"books\":[{\"name\":\"My Book\",\"publicationDate\":1288303200000}]}");
	}

	@Test
	public void testSerializeBookWithCircularDependenciesToJson() throws ParseException, JsonProcessingException {
		String testDate = "29-10-2010";
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = formatter.parse(testDate);

		Author author1 = new Author("NoName Author");
		Book book1 = new Book("My Book", date);
		author1.addBook(book1);
		String json = null;

		ObjectMapper mapper = new ObjectMapper();
		json = mapper.writeValueAsString(book1);

		assertThat(json).isEqualTo("{\"name\":\"My Book\",\"publicationDate\":1288303200000,\"authors\":[{\"name\":\"NoName Author\"}]}");
	}
}
