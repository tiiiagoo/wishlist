package br.tgs.usecase.wishlist;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WishlistFindAllProductsUserCase {

	private final CustomerGateway customerGateway;

	public Wishlist execute(String customerId) {
		return Optional.of(customerGateway.getCustomerById(customerId))
			.map(Customer::getWishlist)
			.orElse(Wishlist.builder()
				.contents(Collections.emptyList()).build());
	}
}
