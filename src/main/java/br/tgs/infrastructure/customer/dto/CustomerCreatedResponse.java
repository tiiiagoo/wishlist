package br.tgs.infrastructure.customer.dto;

public record CustomerCreatedResponse(
	String id,
	String name,
	String email
) {}
