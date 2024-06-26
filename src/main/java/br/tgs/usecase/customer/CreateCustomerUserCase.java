package br.tgs.usecase.customer;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.infrastructure.customer.dto.CreatedCustomerRequest;
import br.tgs.infrastructure.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerUserCase {

	private final CustomerGateway customerGateway;
	private final CustomerMapper customerMapper;

	public Customer execute(CreatedCustomerRequest request) {
		return this.customerGateway.save(customerMapper.toEntity(request));
	}
}
