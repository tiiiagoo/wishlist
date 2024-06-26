package br.tgs.infrastructure.wishlist.dto;

import java.util.List;

public record WishlistResponse(
	List<WishlistContentResponse> contents
) {}
