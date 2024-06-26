package br.tgs.entity.wishlist.model;

import br.tgs.entity.product.model.Product;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistContent {

	private Product product;
	private LocalDateTime desiredAt;

}
