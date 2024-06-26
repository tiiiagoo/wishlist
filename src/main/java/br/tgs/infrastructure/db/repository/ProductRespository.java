package br.tgs.infrastructure.db.repository;

import br.tgs.entity.product.model.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRespository extends MongoRepository<Product, ObjectId> {

}
