package br.tgs.usecase.product;

import br.tgs.entity.product.gateway.ProductGateway;
import br.tgs.entity.product.model.Product;
import br.tgs.infrastructure.mapper.ProductMapper;
import br.tgs.infrastructure.product.dto.CreatedProductRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductUserCase {

	private final ProductGateway productGateway;
	private final ProductMapper productMapper;

	public Product execute(CreatedProductRequest request) {
		return this.productGateway.save(productMapper.toEntity(request));
	}
}
