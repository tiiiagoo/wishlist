package br.tgs.entity.customer.model.valueobjects.wishlist.exception;

public class WishlistException extends RuntimeException{
	public WishlistException() {
		super("Wishlist is full");
	}
}
