package br.tgs.entity.product.model;

import br.tgs.entity.product.enums.ProductType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {

	@BsonId
	private ObjectId id;
	private String name;
	private Double price;
	private ProductType type;
	@Default
	private boolean enabled = true;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
