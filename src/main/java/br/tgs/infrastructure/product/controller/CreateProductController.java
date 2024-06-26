package br.tgs.infrastructure.product.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.CREATED;

import br.tgs.infrastructure.mapper.ProductMapper;
import br.tgs.infrastructure.product.dto.CreatedProductRequest;
import br.tgs.infrastructure.product.dto.ProductCreatedResponse;
import br.tgs.usecase.product.CreateProductUserCase;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateProductController {

	private final CreateProductUserCase createProductUserCase;
	private final ProductMapper mapper;

	@PostMapping("/products")
	@ResponseStatus(CREATED)
	public CompletableFuture<ProductCreatedResponse> createCustomer(
		@Valid @RequestBody CreatedProductRequest request) {
		return supplyAsync(() ->
			mapper.toResponse(createProductUserCase.execute(request)));
	}
}
