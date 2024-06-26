package br.tgs.infrastructure.customer.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.CREATED;

import br.tgs.infrastructure.customer.dto.CreatedCustomerRequest;
import br.tgs.infrastructure.customer.dto.CustomerCreatedResponse;
import br.tgs.infrastructure.mapper.CustomerMapper;
import br.tgs.usecase.customer.CreateCustomerUserCase;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateCustomerController {

	private final CreateCustomerUserCase createCustomerUseCase;
	private final CustomerMapper mapper;

	@PostMapping("/customers")
	@ResponseStatus(CREATED)
	public CompletableFuture<CustomerCreatedResponse> createCustomer(
		@Valid @RequestBody CreatedCustomerRequest request) {
		return supplyAsync(() ->
			 mapper.toResponse(createCustomerUseCase.execute(request)));
	}
}