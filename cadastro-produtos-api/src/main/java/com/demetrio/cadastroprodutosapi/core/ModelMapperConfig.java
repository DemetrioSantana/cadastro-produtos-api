package com.demetrio.cadastroprodutosapi.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demetrio.cadastroprodutosapi.model.Produto;
import com.demetrio.cadastroprodutosapi.model.ProdutoModelDTO;
import com.demetrio.cadastroprodutosapi.model.enuns.UnidadeDeMedida;

@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		var  modelMapper = new ModelMapper();
		modelMapper.createTypeMap(ProdutoModelDTO.class, Produto.class)
		.<UnidadeDeMedida>addMapping(src -> src.getUnidadeDeMedida() , (dest, value) -> dest.setUnidadeDeMedida(value));
		
		return modelMapper;
	}
	
	
}
