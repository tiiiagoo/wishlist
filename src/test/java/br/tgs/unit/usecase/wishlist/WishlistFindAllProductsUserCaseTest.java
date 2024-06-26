package br.tgs.unit.usecase.wishlist;

import static br.tgs.utils.JsonUtil.jsonArrayContent;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.wishlist.model.Wishlist;
import br.tgs.entity.wishlist.model.WishlistContent;
import br.tgs.usecase.wishlist.WishlistFindAllProductsUserCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.SneakyThrows;
import lombok.val;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class WishlistFindAllProductsUserCaseTest {

	private static final String CUSTOMER_1_ID = "66796e363b495363b70d3832";
	private static final LocalDateTime LOCAL_DATE_TIME = of(2024, 6, 7, 10, 0, 0);

	@InjectMocks
	WishlistFindAllProductsUserCase wishlistFindAllProductsUserCase;
	@Mock
	CustomerGateway customerGateway;

	@Test
	void shouldFindAllProductsInWishlist_thenReturnWishlistWithAllProducts() {
		val contents = getWishlistWith20Contents();
		var customer = buildCustomer1();
		var wishlistFull = Wishlist.builder()
			.contents(Arrays.asList(contents))
			.build();
		customer.setWishlist(wishlistFull);
		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(customer);

		val wishlistSelected = wishlistFindAllProductsUserCase.execute(CUSTOMER_1_ID);

		assertEquals(20, wishlistSelected.getContents().size());
	}

	@Test
	void shouldNotFindAnyProductdInWishlistByNewCustomer_thenReturnWishlistEmpty() {
		var customer = buildCustomer1();
		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(customer);

		val wishlist = wishlistFindAllProductsUserCase.execute(CUSTOMER_1_ID);

		assertTrue(wishlist.getContents().isEmpty());
	}

	@SneakyThrows
	private WishlistContent[] getWishlistWith20Contents() {
		val objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		val wishlistContentsJson = jsonArrayContent("wishlist_size_20.json");
		return objectMapper.readValue(wishlistContentsJson, WishlistContent[].class);
	}

	private Customer buildCustomer1() {
		return Customer.builder()
			.id(new ObjectId(CUSTOMER_1_ID))
			.name("Tiago")
			.email("tiago@test.com")
			.createdAt(LOCAL_DATE_TIME)
			.enabled(true)
			.build();
	}
}
