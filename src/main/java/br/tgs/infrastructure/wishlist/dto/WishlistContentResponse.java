package br.tgs.infrastructure.wishlist.dto;

import java.time.LocalDateTime;

public record WishlistContentResponse(
	WishlistProductResponse product,
	LocalDateTime desiredAt
) {}
