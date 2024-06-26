package br.tgs.infrastructure.wishlist.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.OK;

import br.tgs.infrastructure.mapper.WishlistMapper;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import br.tgs.usecase.wishlist.WishlistAddProductUserCase;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistAddProductController {

	private final WishlistAddProductUserCase wishlistAddProductUserCase;
	private final WishlistMapper mapper;

	@PostMapping("/wishlist/customer/{customerId}/products-add/{productId}")
	@ResponseStatus(OK)
	public CompletableFuture<WishlistResponse> add(
		@PathVariable("customerId")	String customerId,
		@PathVariable("productId") String productId) {
		return supplyAsync(() ->
			mapper.toResponse(wishlistAddProductUserCase.execute(customerId, productId)));
	}

}
