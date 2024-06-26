package br.tgs.infrastructure.config.exception;

public class InvalidEntityIdExeception extends RuntimeException {
	public InvalidEntityIdExeception() {
		super("Invalid id");
	}
}
