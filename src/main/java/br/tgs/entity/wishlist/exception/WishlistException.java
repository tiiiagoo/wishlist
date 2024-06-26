package br.tgs.entity.wishlist.exception;

public class WishlistException extends RuntimeException{
	public WishlistException() {
		super("Wishlist is full");
	}
}
