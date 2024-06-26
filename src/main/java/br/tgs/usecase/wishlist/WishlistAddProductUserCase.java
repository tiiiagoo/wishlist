package br.tgs.usecase.wishlist;

import static java.util.Objects.nonNull;

import br.tgs.entity.customer.gateway.CustomerGateway;
import br.tgs.entity.customer.model.Customer;
import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.entity.wishlist.model.Wishlist;
import br.tgs.entity.wishlist.model.WishlistContent;
import br.tgs.entity.wishlist.exception.WishlistException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class WishlistAddProductUserCase {

	private final CustomerGateway customerGateway;
	private final ProductGateway productGateway;

	public Wishlist execute(String customerId, String productId) {
		var customer = customerGateway.getCustomerById(customerId);
		validWishlistSize(customer.getWishlist());
		val product = productGateway.getProductById(productId);
		updateWishlist(customer, product);
		return customer.getWishlist();
	}

	private void validWishlistSize(Wishlist wishlist) {
		 Optional.ofNullable(wishlist)
			 .filter(wl -> nonNull(wl.getContents()) && !wl.getContents().isEmpty())
			 .map(Wishlist::getContents)
			 .filter(contents -> contents.size() == 20)
			 .ifPresent(contents -> { throw new WishlistException();});
	}

	private void updateWishlist(Customer customer, Product product) {
		Optional.ofNullable(customer.getWishlist())
			.ifPresentOrElse(
			wishlist -> wishlistContentsAddProduct(wishlist, product),
			() -> customer.setWishlist(buildWishlist(product)));
		customer.setUpdatedAt(LocalDateTime.now());
		customerGateway.save(customer);
	}

	private void wishlistContentsAddProduct(Wishlist wishlist, Product product) {
		var contents = wishlist.getContents();
		validContentsToAddProduct(contents, product);
		wishlist.setContents(contents);
	}

	private void validContentsToAddProduct(List<WishlistContent> contents, Product product) {
		if (product.isEnabled() && productNotInWishlist(contents, product)) {
			contents.add(buildContent(product));
		}
	}

	private boolean productNotInWishlist(List<WishlistContent> contents, Product product) {
		return contents.stream().noneMatch(content ->
			content.getProduct().getId().equals(product.getId()));
	}

	private Wishlist buildWishlist(Product product) {
		return Wishlist.builder()
			.contents(List.of(WishlistContent.builder()
				.product(product)
				.desiredAt(LocalDateTime.now())
				.build()))
			.build();
	}

	private WishlistContent buildContent(Product product) {
		return WishlistContent.builder()
			.product(product)
			.desiredAt(LocalDateTime.now())
			.build();
	}
}
