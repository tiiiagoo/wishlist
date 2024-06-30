package br.tgs.infrastructure.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

import br.tgs.infrastructure.product.gateway.ProductDatabaseGateway;
import br.tgs.infrastructure.wishlist.dto.WishlistContentResponse;
import br.tgs.infrastructure.wishlist.dto.WishlistProductResponse;
import lombok.val;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public abstract class WishlistAbstractMapper {

	ProductDatabaseGateway productGateway;

	@Autowired
	public void setProductGateway(ProductDatabaseGateway productGateway) {
		this.productGateway = productGateway;
	}

	@Mapping(target = "product", expression = "java(getProductById(productId))")
	@Mapping(target = "desiredAt", ignore = true)
	abstract WishlistContentResponse toResponse(String productId);

	public WishlistProductResponse getProductById(String productId) {
		val product = this.productGateway.getProductById(productId);
		return new WishlistProductResponse(product.getId().toHexString(),
			product.getName(), product.getPrice(), product.getType());
	}
}
