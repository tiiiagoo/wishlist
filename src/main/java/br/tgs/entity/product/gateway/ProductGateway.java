package br.tgs.entity.product.gateway;

import br.tgs.entity.product.model.Product;

public interface ProductGateway {
	Product save(Product product);
	Product getProductById(String productId);
}
