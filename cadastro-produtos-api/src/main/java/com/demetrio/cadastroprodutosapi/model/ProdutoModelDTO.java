package com.demetrio.cadastroprodutosapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.demetrio.cadastroprodutosapi.model.enuns.UnidadeDeMedida;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoModelDTO {
	
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nomeDoItem;
	
	
	private UnidadeDeMedida unidadeDeMedida;
	
	private Long quantidade;
	
	private BigDecimal preco;
	
	private Boolean produtoPerecivel;
	
	private LocalDate dataDeValidade;
	
	private LocalDate dataDeFabricacao;
	
	@JsonIgnore
	public boolean isValido() {
		return UnidadeDeMedida.LITRO.equals(unidadeDeMedida) ||
				UnidadeDeMedida.QUILO .equals(unidadeDeMedida)
				|| UnidadeDeMedida.UNIDADE.equals(unidadeDeMedida); 
	}
	

}
