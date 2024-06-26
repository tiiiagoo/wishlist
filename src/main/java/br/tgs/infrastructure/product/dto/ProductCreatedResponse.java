package br.tgs.infrastructure.product.dto;

import br.tgs.entity.product.enums.ProductType;

public record ProductCreatedResponse(
	String id,
	String name,
	Double price,
	ProductType type
) {}
