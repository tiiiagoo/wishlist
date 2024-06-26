package br.tgs.infrastructure.mapper;

import br.tgs.entity.wishlist.model.Wishlist;
import br.tgs.entity.wishlist.model.WishlistContent;
import br.tgs.infrastructure.wishlist.dto.WishlistContentResponse;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface WishlistMapper {

	WishlistResponse toResponse(Wishlist wishlist);
	WishlistContentResponse toResponse(WishlistContent wishlistContent);
}
