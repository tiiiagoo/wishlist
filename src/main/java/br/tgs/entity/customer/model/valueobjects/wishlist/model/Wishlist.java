package br.tgs.entity.customer.model.valueobjects.wishlist.model;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
	private List<WishlistContent> contents;
}