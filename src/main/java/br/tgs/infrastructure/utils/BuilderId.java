package br.tgs.infrastructure.utils;

import static lombok.AccessLevel.PRIVATE;

import br.tgs.infrastructure.config.exception.InvalidEntityIdExeception;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;

@RequiredArgsConstructor(access = PRIVATE)
public class BuilderId {

	public static ObjectId getId(String id) {
		try {
			return new ObjectId(id);
		} catch (IllegalArgumentException e) {
			throw new InvalidEntityIdExeception();
		}
	}
}
