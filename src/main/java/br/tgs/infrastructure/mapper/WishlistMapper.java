package br.tgs.infrastructure.mapper;

import br.tgs.entity.customer.model.valueobjects.wishlist.model.Wishlist;
import br.tgs.entity.customer.model.valueobjects.wishlist.model.WishlistContent;
import br.tgs.infrastructure.wishlist.dto.WishlistContentResponse;
import br.tgs.infrastructure.wishlist.dto.WishlistResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {WishlistAbstractMapper.class})
public interface WishlistMapper {

	WishlistResponse toResponse(Wishlist wishlist);

	@Mapping(target = "product", source = "productId")
	WishlistContentResponse toResponse(WishlistContent wishlistContent);

}
