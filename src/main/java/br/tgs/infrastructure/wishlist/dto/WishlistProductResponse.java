package br.tgs.infrastructure.wishlist.dto;

import br.tgs.entity.product.enums.ProductType;

public record WishlistProductResponse(
	String id,
	String name,
	Double price,
	ProductType type
) {}
