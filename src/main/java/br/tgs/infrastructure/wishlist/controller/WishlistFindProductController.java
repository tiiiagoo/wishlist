package br.tgs.infrastructure.wishlist.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.OK;

import br.tgs.infrastructure.mapper.WishlistMapper;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import br.tgs.usecase.wishlist.WishlistFindProductUserCase;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistFindProductController {

	private final WishlistFindProductUserCase wishlistFindProductUserCase;
	private final WishlistMapper mapper;

	@GetMapping("/wishlist/customer/{customerId}/products/{productId}")
	@ResponseStatus(OK)
	public CompletableFuture<WishlistResponse> findProduct(
		@PathVariable("customerId") String customerId,
		@PathVariable("productId") String productId) {
		return supplyAsync(() ->
			mapper.toResponse(wishlistFindProductUserCase.execute(customerId, productId)));
	}
}
