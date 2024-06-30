package br.tgs.usecase.wishlist;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.WishlistContent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class WishlistFindProductUserCase {

	private final CustomerGateway customerGateway;

	public Wishlist execute(String customerId, String productId) {
		val customer = customerGateway.getCustomerById(customerId);
		return getWishlisSpeficProduct(customer, productId);
	}

	public Wishlist getWishlisSpeficProduct(Customer customer, String productId) {
		var wishlist = getWishlist(customer.getWishlist());
		var content = new ArrayList<>(getWishlistContents(customer));
		wishlist.setContents(content);
		Optional.ofNullable(getContentSelected(wishlist, productId))
			.ifPresentOrElse(seleted -> wishlist.setContents(List.of(seleted)),
				() -> wishlist.setContents(Collections.emptyList()));
		return wishlist;
	}

	private Wishlist getWishlist(Wishlist wishlist) {
		return Optional.ofNullable(wishlist).orElse(new Wishlist());
	}

	private List<WishlistContent> getWishlistContents(Customer customer) {
		return Optional.ofNullable(customer.getWishlist())
			.map(Wishlist::getContents)
			.orElse(new ArrayList<>());
	}

	private WishlistContent getContentSelected(Wishlist wishlist, String productId) {
		return wishlist.getContents().stream().toList().stream()
			.filter(content ->  content.getProductId().equals(productId))
			.findFirst().orElse(null);
	}
}
