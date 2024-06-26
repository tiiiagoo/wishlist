package br.tgs.infrastructure.customer.dto;

import br.tgs.infrastructure.customer.validation.unique.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreatedCustomerRequest(
	@NotBlank
	String name,
	@NotBlank
	@Email
	@UniqueEmail
	String email
) {}
