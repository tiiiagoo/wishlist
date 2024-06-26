package br.tgs.unit.usecase.product;

import static br.tgs.entity.product.enums.ProductType.CELL_PHONES;
import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.infrastructure.mapper.ProductMapper;
import br.tgs.infrastructure.product.dto.CreatedProductRequest;
import br.tgs.usecase.product.CreateProductUserCase;
import lombok.val;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateProductUserCaseTest {

	@InjectMocks
	CreateProductUserCase createProductUserCase;
	@Mock
	ProductGateway productGateway;
	@Mock
	ProductMapper productMapper;

	private static final String NAME = "IPhone 15";
	private static final Double PRICE = 7000.00;

	@Test
	void shouldCreateNewProduct_thenReturnProductCreated() {
		val request = new CreatedProductRequest(NAME, PRICE, CELL_PHONES);
		val customer = Product.builder()
			.id(new ObjectId())
			.name(NAME)
			.price(PRICE)
			.type(CELL_PHONES)
			.build();
		when(productMapper.toEntity(request)).thenReturn(customer);
		when(productGateway.save(customer)).thenReturn(customer);

		val customerCreated = createProductUserCase.execute(request);

		assertNotNull(customerCreated);
		assertNotNull(customerCreated.getId());
		assertEquals(NAME, customerCreated.getName());
		assertEquals(PRICE, customerCreated.getPrice());
		assertEquals(CELL_PHONES, customerCreated.getType());
	}
}
