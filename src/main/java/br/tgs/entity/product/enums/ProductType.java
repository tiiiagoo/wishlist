package br.tgs.entity.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductType {
	HOUSEHOLD_APPLIANCE("Eletrodoméstico"),
	CELL_PHONES("Celulares"),
	FURNITURE("Móveis"),
	TV_VIDEO("Tv e Vídeo"),
	COMPUTING("Informática");

	private final String description;

}
