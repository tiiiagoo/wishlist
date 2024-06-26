package br.tgs.infrastructure.customer.gateway;

import static br.tgs.infrastructure.utils.BuilderId.getId;

import br.tgs.entity.customer.exception.CustomerNotFoundException;
import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.infrastructure.db.repository.CustomerRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerDatabaseGateway implements CustomerGateway {

	private final CustomerRespository respository;

	@Override
	public Customer save(Customer customer) {
		return this.respository.save(customer);
	}

	@Override
	public Customer getCustomerById(String customerId) {
		return respository.findById(getId(customerId))
			.orElseThrow(CustomerNotFoundException::new);
	}
}
