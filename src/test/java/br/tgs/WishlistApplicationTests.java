package br.tgs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@EnableAutoConfiguration
@ActiveProfiles("test")
class WishlistApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true);
	}

}
