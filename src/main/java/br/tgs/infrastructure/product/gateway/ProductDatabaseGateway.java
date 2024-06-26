package br.tgs.infrastructure.product.gateway;

import static br.tgs.infrastructure.utils.BuilderId.getId;

import br.tgs.entity.product.exception.ProductNotFoundException;
import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.infrastructure.db.repository.ProductRespository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductDatabaseGateway implements ProductGateway {

	private final ProductRespository respository;

	public Product save(Product product) {
		return this.respository.save(product);
	}

	@Override
	public Product getProductById(String productId) {
		return respository.findById(getId(productId))
			.orElseThrow(ProductNotFoundException::new);
	}
}
