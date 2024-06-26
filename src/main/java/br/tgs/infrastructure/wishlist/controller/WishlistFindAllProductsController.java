package br.tgs.infrastructure.wishlist.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.OK;

import br.tgs.infrastructure.mapper.WishlistMapper;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import br.tgs.usecase.wishlist.WishlistFindAllProductsUserCase;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistFindAllProductsController {

	private final WishlistFindAllProductsUserCase wishlistFindAllProductsUserCase;
	private final WishlistMapper mapper;

	@GetMapping("/wishlist/customer/{customerId}/all-products")
	@ResponseStatus(OK)
	public CompletableFuture<WishlistResponse> findAllProduct(
		@PathVariable("customerId") String customerId) {
		return supplyAsync(() ->
			mapper.toResponse(wishlistFindAllProductsUserCase.execute(customerId)));
	}
}
