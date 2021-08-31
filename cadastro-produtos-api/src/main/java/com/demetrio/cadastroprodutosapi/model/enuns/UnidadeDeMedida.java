package com.demetrio.cadastroprodutosapi.model.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = Shape.OBJECT)
public enum UnidadeDeMedida {
	LITRO("lt"),
	QUILO("kg"),
	UNIDADE("un");
	
	private final String name;
	
	
	@JsonCreator
	private UnidadeDeMedida(String name) {
		this.name = name;
	}
	
	
	public String toString() {
		return this.name();
	}

	
	public String getName() {
		return name;
	}
	
	@JsonCreator
	public static UnidadeDeMedida fromJson(@JsonProperty("name") String name) {
		return valueOf(name);
	}

}
