package br.tgs.infrastructure.customer.validation.unique;

import br.tgs.infrastructure.db.repository.CustomerRespository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final CustomerRespository repository;

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate("{unique.email.customer}")
        .addConstraintViolation();
    context.getDefaultConstraintMessageTemplate();
    return repository.findByEmail(email).isEmpty();
  }

}