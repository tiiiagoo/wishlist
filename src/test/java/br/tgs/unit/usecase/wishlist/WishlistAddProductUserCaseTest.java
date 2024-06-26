package br.tgs.unit.usecase.wishlist;

import static br.tgs.entity.product.enums.ProductType.CELL_PHONES;
import static br.tgs.entity.product.enums.ProductType.FURNITURE;
import static br.tgs.utils.JsonUtil.jsonArrayContent;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import br.tgs.entity.wishlist.exception.WishlistException;
import br.tgs.usecase.wishlist.WishlistAddProductUserCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
class WishlistAddProductUserCaseTest {

	private static final String CUSTOMER_1_ID = "66796e363b495363b70d3832";
	private static final String CUSTOMER_2_ID = "667b2b402611d376f83a01f8";
	private static final String PRODUCT_1_ID = "66797864cc88591b111115c0";
	private static final String PRODUCT_2_ID = "66797aee2847f677df8ad2bb";
	private static final LocalDateTime LOCAL_DATE_TIME = of(2024, 6, 7, 10, 0, 0);

	@InjectMocks
	WishlistAddProductUserCase wishlistAddProductUserCase;
	@Mock
	CustomerGateway customerGateway;
	@Mock
	ProductGateway productGateway;

	@Test
	void shouldAddNewProductdInWishlistFirstTime_thenReturnWishlistWithProductAdded() {
		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(buildCustomer1());
		when(productGateway.getProductById(PRODUCT_1_ID))
			.thenReturn(buildProduct1());

		val wishlist = wishlistAddProductUserCase.execute(CUSTOMER_1_ID, PRODUCT_1_ID);

		assertEquals(1, wishlist.getContents().size());
		verify(customerGateway, times(1)).save(any());
	}

	@Test
	void shouldAddNewProductdInWishlist_thenReturnWishlistWithProductAdded() {
		when(customerGateway.getCustomerById(CUSTOMER_2_ID))
			.thenReturn(buildCustomer2());
		when(productGateway.getProductById(PRODUCT_2_ID))
			.thenReturn(buildProduct2());

		val wishlist = wishlistAddProductUserCase.execute(CUSTOMER_2_ID, PRODUCT_2_ID);

		assertEquals(2, wishlist.getContents().size());
		verify(customerGateway, times(1)).save(any());
	}

	@Test
	@SneakyThrows
	void shouldNotAddNewProductdInWishlistThatSizeMoreThan20_thenReturnWishlistException() {
		val contents = getWishlistWith20Contents();
		var customer = buildCustomer1();
		var wishlistFull = Wishlist.builder()
			.contents(Arrays.asList(contents))
			.build();
		customer.setWishlist(wishlistFull);

		when(customerGateway.getCustomerById(CUSTOMER_1_ID))
			.thenReturn(customer);
		when(productGateway.getProductById(PRODUCT_1_ID))
			.thenReturn(buildProduct2());

		val exception = assertThrows(WishlistException.class, ()->
			wishlistAddProductUserCase.execute(CUSTOMER_1_ID, PRODUCT_1_ID));

		assertEquals("Wishlist is full", exception.getMessage());
	}

	@SneakyThrows
	private WishlistContent[] getWishlistWith20Contents() {
		val objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		val wishlistContentsJson = jsonArrayContent("wishlist_size_20.json");
		return objectMapper.readValue(wishlistContentsJson, WishlistContent[].class);
	}

	private Product buildProduct1() {
		return Product.builder()
			.id(new ObjectId(PRODUCT_1_ID))
			.name("IPhone")
			.price(5000.00)
			.type(CELL_PHONES)
			.createdAt(LOCAL_DATE_TIME)
			.enabled(true)
			.build();
	}

	private Product buildProduct2() {
		return Product.builder()
			.id(new ObjectId(PRODUCT_2_ID))
			.name("Cadeira Gamer")
			.price(1000.00)
			.type(FURNITURE)
			.createdAt(LOCAL_DATE_TIME)
			.enabled(true)
			.build();
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

	private Customer buildCustomer2() {
		var contents = new ArrayList<WishlistContent>();
		contents.add(WishlistContent.builder()
			.product(buildProduct1())
			.desiredAt(LOCAL_DATE_TIME)
			.build());
		return Customer.builder()
			.id(new ObjectId(CUSTOMER_1_ID))
			.name("Tiago")
			.email("tiago@test.com")
			.wishlist(Wishlist.builder().contents(contents).build())
			.enabled(true)
			.build();
	}
}
