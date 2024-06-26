package br.tgs.unit.usecase.wishlist;

import static br.tgs.entity.product.enums.ProductType.FURNITURE;
import static br.tgs.utils.JsonUtil.jsonArrayContent;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.entity.wishlist.model.Wishlist;
import br.tgs.entity.wishlist.model.WishlistContent;
import br.tgs.usecase.wishlist.WishlistRemoveProductUserCase;
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
class WishlistRemoveProductUserCaseTest {

	private static final String CUSTOMER_1_ID = "66796e363b495363b70d3832";
	private static final String PRODUCT_1_ID = "6679817d2847f677df8ad2cb";
	private static final LocalDateTime LOCAL_DATE_TIME = of(2024, 6, 7, 10, 0, 0);

	@InjectMocks
	WishlistRemoveProductUserCase wishlistRemoveProductUserCase;
	@Mock
	CustomerGateway customerGateway;
	@Mock
	ProductGateway productGateway;

	@Test
	void shouldRemoveProductdInWishlist_thenReturnWishlistWithProductRemoved() {
		val contents = getWishlistWith20Contents();
		var customer = buildCustomer1();
		var wishlistFull = Wishlist.builder()
			.contents(Arrays.asList(contents))
			.build();
		customer.setWishlist(wishlistFull);

		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(customer);
		when(productGateway.getProductById(PRODUCT_1_ID))
			.thenReturn(buildProduct1());

		val wishlist = wishlistRemoveProductUserCase.execute(CUSTOMER_1_ID, PRODUCT_1_ID);

		assertEquals(19, wishlist.getContents().size());
		verify(customerGateway, times(1)).save(any());
	}

	@Test
	void shouldNotRemoveProductdInWishlistEmpty_thenReturnWishlistEmpty() {
		var customer = buildCustomer1();
		customer.setWishlist(null);

		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(customer);
		when(productGateway.getProductById(PRODUCT_1_ID))
			.thenReturn(buildProduct1());

		val wishlist = wishlistRemoveProductUserCase.execute(CUSTOMER_1_ID, PRODUCT_1_ID);

		assertEquals(0, wishlist.getContents().size());
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

	private Product buildProduct1() {
		return Product.builder()
			.id(new ObjectId(PRODUCT_1_ID))
			.name("Cadeira de Escritório Secretária Base Cromada com Rodinha Fortt Lisboa - CSF02")
			.price(179.9)
			.type(FURNITURE)
			.enabled(true)
			.build();
	}
}
