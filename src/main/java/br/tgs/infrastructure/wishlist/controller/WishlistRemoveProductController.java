package br.tgs.infrastructure.wishlist.controller;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static org.springframework.http.HttpStatus.OK;

import br.tgs.infrastructure.mapper.WishlistMapper;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import br.tgs.usecase.wishlist.WishlistRemoveProductUserCase;
import jakarta.validation.constraints.Size;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WishlistRemoveProductController {

	private final WishlistRemoveProductUserCase wishlistRemoveProductUserCase;
	private final WishlistMapper mapper;

	@DeleteMapping("/wishlist/customer/{customerId}/remove-products/{productId}")
	@ResponseStatus(OK)
	public CompletableFuture<WishlistResponse> remove(
		@PathVariable("customerId") @Size(min = 24) String customerId,
		@PathVariable("productId") @Size(min = 24) String productId) {
		return supplyAsync(() ->
			mapper.toResponse(wishlistRemoveProductUserCase.execute(customerId, productId)));
	}
}
