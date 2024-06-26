package br.tgs.infrastructure.db.repository;

import br.tgs.entity.customer.model.Customer;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRespository extends MongoRepository<Customer, ObjectId> {

	Optional<Customer> findByEmail(String email);
}
