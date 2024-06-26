package br.tgs.infrastructure.mapper;

import br.tgs.entity.customer.model.Customer;
import br.tgs.infrastructure.customer.dto.CreatedCustomerRequest;
import br.tgs.infrastructure.customer.dto.CustomerCreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

	@Mapping(target = "wishlist", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	Customer toEntity(CreatedCustomerRequest request);

	@Mapping(target = "id", expression = "java(customer.getId().toHexString())")
	CustomerCreatedResponse toResponse(Customer customer);
}
