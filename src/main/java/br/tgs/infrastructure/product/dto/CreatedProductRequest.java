package br.tgs.infrastructure.product.dto;

import br.tgs.entity.product.enums.ProductType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatedProductRequest(
	@NotBlank
	String name,
	@NotNull
	@Min(1)
	Double price,
	@NotNull
	ProductType type
) {}
