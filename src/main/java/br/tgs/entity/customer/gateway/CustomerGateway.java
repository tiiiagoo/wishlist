package br.tgs.entity.customer.gateway;

import br.tgs.entity.customer.model.Customer;

public interface CustomerGateway {
	Customer getCustomerById(String customerId);
	Customer save(Customer customer);
}
