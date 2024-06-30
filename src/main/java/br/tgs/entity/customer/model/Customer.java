package br.tgs.entity.customer.model;

import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Customer {

	@Id
	private ObjectId id;
	private String name;
	@Indexed
	private String email;
	@Default
	private boolean enabled = true;
	@Setter
	private Wishlist wishlist;
	private LocalDateTime createdAt;
	@Setter
	private LocalDateTime updatedAt;
}
