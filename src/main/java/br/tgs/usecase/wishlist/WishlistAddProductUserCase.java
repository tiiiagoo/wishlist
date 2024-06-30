package br.tgs.usecase.wishlist;

import static java.util.Objects.nonNull;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.WishlistContent;
import br.tgs.entity.customer.model.valueobjects.wishlist.exception.WishlistException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WishlistAddProductUserCase {

	private final CustomerGateway customerGateway;

	public Wishlist execute(String customerId, String productId) {
		var customer = customerGateway.getCustomerById(customerId);
		validWishlistSize(customer.getWishlist());
		updateWishlist(customer, productId);
		return customer.getWishlist();
	}

	private void validWishlistSize(Wishlist wishlist) {
		 Optional.ofNullable(wishlist)
			 .filter(wl -> nonNull(wl.getContents()) && !wl.getContents().isEmpty())
			 .map(Wishlist::getContents)
			 .filter(contents -> contents.size() == 20)
			 .ifPresent(contents -> { throw new WishlistException();});
	}

	private void updateWishlist(Customer customer, String productId) {
		Optional.ofNullable(customer.getWishlist())
			.ifPresentOrElse(
			wishlist -> wishlistContentsAddProduct(wishlist, productId),
			() -> customer.setWishlist(buildWishlist(productId)));
		customer.setUpdatedAt(LocalDateTime.now());
		customerGateway.save(customer);
	}

	private void wishlistContentsAddProduct(Wishlist wishlist, String productId) {
		var contents = wishlist.getContents();
		validContentsToAddProduct(contents, productId);
		wishlist.setContents(contents);
	}

	private void validContentsToAddProduct(List<WishlistContent> contents, String productId) {
		if (productNotInWishlist(contents, productId)) {
			contents.add(buildContent(productId));
		}
	}

	private boolean productNotInWishlist(List<WishlistContent> contents, String productId) {
		return contents.stream().noneMatch(content ->
			content.getProductId().equals(productId));
	}

	private Wishlist buildWishlist(String productId) {
		return Wishlist.builder()
			.contents(List.of(WishlistContent.builder()
				.productId(productId)
				.desiredAt(LocalDateTime.now())
				.build()))
			.build();
	}

	private WishlistContent buildContent(String productId) {
		return WishlistContent.builder()
			.productId(productId)
			.desiredAt(LocalDateTime.now())
			.build();
	}
}