package br.tgs.infrastructure.config.web;

import br.tgs.infrastructure.customer.gateway.CustomerDatabaseGateway;
import br.tgs.infrastructure.db.repository.CustomerRespository;
import br.tgs.infrastructure.db.repository.ProductRespository;
import br.tgs.infrastructure.mapper.CustomerMapper;
import br.tgs.infrastructure.mapper.ProductMapper;
import br.tgs.infrastructure.product.gateway.ProductDatabaseGateway;
import br.tgs.usecase.customer.CreateCustomerUserCase;
import br.tgs.usecase.product.CreateProductUserCase;
import br.tgs.usecase.wishlist.WishlistAddProductUserCase;
import br.tgs.usecase.wishlist.WishlistFindAllProductsUserCase;
import br.tgs.usecase.wishlist.WishlistFindProductUserCase;
import br.tgs.usecase.wishlist.WishlistRemoveProductUserCase;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

	@Bean
	public CreateCustomerUserCase createCustomerUseCase(
		CustomerRespository customerRepository, CustomerMapper mapper) {
		val customerGateway = new CustomerDatabaseGateway(customerRepository);
		return new CreateCustomerUserCase(customerGateway, mapper);
	}

	@Bean
	public CreateProductUserCase createProductUseCase(
		ProductRespository productRespository, ProductMapper mapper) {
		val productGateway = new ProductDatabaseGateway(productRespository);
		return new CreateProductUserCase(productGateway, mapper);
	}

	@Bean
	public WishlistAddProductUserCase wishlistAddProductUserCase(
		CustomerRespository customerRepository, ProductRespository productRespository) {
		val customerGateway = new CustomerDatabaseGateway(customerRepository);
		val productGateway = new ProductDatabaseGateway(productRespository);
		return new WishlistAddProductUserCase(customerGateway, productGateway);
	}

	@Bean
	public WishlistRemoveProductUserCase wishlistRemoveProductUserCase(
		CustomerRespository customerRepository, ProductRespository productRespository) {
		val customerGateway = new CustomerDatabaseGateway(customerRepository);
		val productGateway = new ProductDatabaseGateway(productRespository);
		return new WishlistRemoveProductUserCase(customerGateway, productGateway);
	}

	@Bean
	public WishlistFindProductUserCase wishlistFindProductUserCase(
		CustomerRespository customerRepository, ProductRespository productRespository) {
		val customerGateway = new CustomerDatabaseGateway(customerRepository);
		val productGateway = new ProductDatabaseGateway(productRespository);
		return new WishlistFindProductUserCase(customerGateway, productGateway);
	}

	@Bean
	public WishlistFindAllProductsUserCase wishlistFindAllProductUserCase(
		CustomerRespository customerRepository, ProductRespository productRespository) {
		val customerGateway = new CustomerDatabaseGateway(customerRepository);
		return new WishlistFindAllProductsUserCase(customerGateway);
	}
}
