package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookIsCreated() {
		var book = Book.of("1234567890", "Title", "Author", "O Reily", 9.90);
		webTestClient.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(book1 -> {
					assertThat(book1).isNotNull();
					assertThat(book1.isbn()).isEqualTo(book.isbn());
				});
	}

}
