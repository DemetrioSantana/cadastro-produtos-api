package com.demetrio.cadastroprodutosapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.demetrio.cadastroprodutosapi.model.enuns.UnidadeDeMedida;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produtos")
public class Produto {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_do_item", nullable = true)
	private String nomeDoItem;
	
	@Column(name = "unidade_de_medida", nullable = true)
	@Enumerated(value = EnumType.STRING)
	private UnidadeDeMedida unidadeDeMedida;
	
	@Column(name = "quantidade")
	private Long quantidade;
	
	@Column(name = "preco", nullable = true)
	private BigDecimal preco;
	
	@Column(name = "produto_perecivel",  nullable = true)
	private Boolean produtoPerecivel;
	
	@Column(name = "data_de_validade")
	private LocalDate dataDeValidade;
	
	@Column(name = "data_de_fabricacao", nullable = true)
	private LocalDate dataDeFabricacao;
	
	@Transient
	@JsonIgnore
	public boolean isValido() {
		return UnidadeDeMedida.LITRO.equals(unidadeDeMedida) ||
				UnidadeDeMedida.QUILO .equals(unidadeDeMedida)
				|| UnidadeDeMedida.UNIDADE.equals(unidadeDeMedida); 
	}
	

}
