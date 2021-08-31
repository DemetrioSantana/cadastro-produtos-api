package com.demetrio.cadastroprodutosapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.demetrio.cadastroprodutosapi.model.Produto;
import com.demetrio.cadastroprodutosapi.model.ProdutoModelDTO;
import com.demetrio.cadastroprodutosapi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProdutoModelDTO atualizar (Long id, ProdutoModelDTO produto) {
		
		Produto produtoSalvo = this.repository.getById(id);
		if (produtoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(paraEntidadeProduto(produto), produtoSalvo, "id");
		return paraProdutoModelDTO(this.repository.save(produtoSalvo));
		}
		

		public ProdutoModelDTO buscarPorId(Long id) {
			return paraProdutoModelDTO(this.repository.getById(id));
		}
	
		public ProdutoModelDTO salvar(ProdutoModelDTO produto) {
			var entidade = paraEntidadeProduto(produto);
			return paraProdutoModelDTO(this.repository.save(entidade));
		}
		
		public ProdutoModelDTO paraProdutoModelDTO(Produto produto) {
				return this.modelMapper.map(produto, ProdutoModelDTO.class);
		}
		
		
		public Produto paraEntidadeProduto(ProdutoModelDTO produtoModelDTO) {
			return this.modelMapper.map(produtoModelDTO, Produto.class);
		}
	
}
