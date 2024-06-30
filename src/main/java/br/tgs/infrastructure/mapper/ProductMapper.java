package br.tgs.infrastructure.mapper;

import br.tgs.entity.product.model.Product;
import br.tgs.infrastructure.product.dto.CreatedProductRequest;
import br.tgs.infrastructure.product.dto.ProductCreatedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
	@Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
	Product toEntity(CreatedProductRequest request);

	@Mapping(target = "id", expression = "java(product.getId().toHexString())")
	ProductCreatedResponse toResponse(Product product);

}
