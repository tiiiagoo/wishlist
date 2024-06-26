package br.tgs.usecase.wishlist;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.entity.wishlist.model.Wishlist;
import br.tgs.entity.wishlist.model.WishlistContent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class WishlistFindProductUserCase {

	private final CustomerGateway customerGateway;
	private final ProductGateway productGateway;

	public Wishlist execute(String customerId, String productId) {
		val customer = customerGateway.getCustomerById(customerId);
		val product = productGateway.getProductById(productId);
		return getWishlisSpeficProduct(customer, product);
	}

	public Wishlist getWishlisSpeficProduct(Customer customer, Product product) {
		var wishlist = getWishlist(customer.getWishlist());
		var content = new ArrayList<>(getWishlistContents(customer));
		wishlist.setContents(content);
		Optional.ofNullable(getContentSelected(wishlist, product))
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

	private WishlistContent getContentSelected(Wishlist wishlist, Product product) {
		return wishlist.getContents().stream().toList().stream()
			.filter(content ->  content.getProduct().getId().equals(product.getId()))
			.findFirst().orElse(null);
	}
}
