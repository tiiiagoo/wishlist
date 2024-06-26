package br.tgs.unit.usecase.customer;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.infrastructure.customer.dto.CreatedCustomerRequest;
import br.tgs.infrastructure.mapper.CustomerMapper;
import br.tgs.usecase.customer.CreateCustomerUserCase;
import lombok.val;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateCustomerUserCaseTest {

	@InjectMocks
	CreateCustomerUserCase createCustomerUserCase;
	@Mock
	CustomerGateway customerGateway;
	@Mock
	CustomerMapper customerMapper;

	private static final String NAME = "Tiago";
	private static final String EMAIL = "tiago@email.com";

	@Test
	void shouldCreateNewCustomer_thenReturnCustomerCreated() {
		val request = new CreatedCustomerRequest(NAME, EMAIL);
		val customer = Customer.builder()
			.name(NAME)
			.email(EMAIL)
			.id(new ObjectId())
			.build();
		when(customerMapper.toEntity(request)).thenReturn(customer);
		when(customerGateway.save(customer)).thenReturn(customer);

		val customerCreated = createCustomerUserCase.execute(request);

		assertNotNull(customerCreated);
		assertNotNull(customerCreated.getId());
		assertEquals(NAME, customerCreated.getName());
		assertEquals(EMAIL, customerCreated.getEmail());
	}
}
