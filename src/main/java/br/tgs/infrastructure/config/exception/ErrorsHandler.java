package br.tgs.infrastructure.config.exception;

import br.tgs.entity.customer.exception.CustomerNotFoundException;
import br.tgs.entity.product.exception.ProductNotFoundException;
import br.tgs.entity.wishlist.exception.WishlistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<String> customerNotFound(CustomerNotFoundException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<String> wishlist(ProductNotFoundException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(InvalidEntityIdExeception.class)
  public ResponseEntity<String> invalidEntityId(InvalidEntityIdExeception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(WishlistException.class)
  public ResponseEntity<String> wishlist(WishlistException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

}