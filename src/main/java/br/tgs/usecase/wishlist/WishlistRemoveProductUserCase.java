package br.tgs.usecase.wishlist;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.WishlistContent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WishlistRemoveProductUserCase {

	private final CustomerGateway customerGateway;

	public Wishlist execute(String customerId, String productId) {
		var customer = customerGateway.getCustomerById(customerId);
		return updateWishlistProductRemoved(customer, productId);
	}

	private Wishlist updateWishlistProductRemoved(Customer customer, String productId) {
		var wishlist = getWishlist(customer.getWishlist());
		var contents = new ArrayList<>(getWishlistContents(customer));
		wishlist.setContents(contents);
		Optional.ofNullable(getContentToRemove(wishlist, productId))
			.ifPresent(remove -> updatedIdRemovedProduct(customer, remove, contents));
		return Optional.ofNullable(customer.getWishlist()).orElse(wishlist);
	}

	private Wishlist getWishlist(Wishlist wishlist) {
		return Optional.ofNullable(wishlist).orElse(new Wishlist());
	}

	private List<WishlistContent> getWishlistContents(Customer customer) {
		return Optional.ofNullable(customer.getWishlist())
			.map(Wishlist::getContents)
			.orElse(new ArrayList<>());
	}

	private WishlistContent getContentToRemove(Wishlist wishlist, String productId) {
		return wishlist.getContents().stream().toList().stream()
			.filter(content ->  content.getProductId().equals(productId))
			.findFirst().orElse(null);
	}

	private void updatedIdRemovedProduct(Customer customer, WishlistContent remove,
		List<WishlistContent> contents) {
		contents.remove(remove);
		customer.getWishlist().setContents(contents);
		customer.setUpdatedAt(LocalDateTime.now());
		customerGateway.save(customer);
	}
}
