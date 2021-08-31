package com.demetrio.cadastroprodutosapi.controllers;
/**
 * Controller 
 */
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demetrio.cadastroprodutosapi.event.RecursoCriadoEvent;
import com.demetrio.cadastroprodutosapi.model.Produto;
import com.demetrio.cadastroprodutosapi.model.ProdutoModelDTO;
import com.demetrio.cadastroprodutosapi.repository.ProdutoRepository;
import com.demetrio.cadastroprodutosapi.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class ProdutoController {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<ProdutoModelDTO> salvar(@Validated @RequestBody ProdutoModelDTO produto, HttpServletResponse response) {
		
		ProdutoModelDTO produtoSalvo = this.produtoService.salvar(produto);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, response, produtoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoModelDTO> atualizar(@PathVariable Long id, @Validated @RequestBody ProdutoModelDTO produto) {
		ProdutoModelDTO produtoSalvo = this.produtoService.atualizar(id, produto);
		return ResponseEntity.ok(produtoSalvo);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModelDTO> buscarPorId(@PathVariable Long id) {
		ProdutoModelDTO produto = this.produtoService.buscarPorId(id);
		return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
	}
	
	@GetMapping()
	public List<ProdutoModelDTO> listar() {
		return this.repository.findAll()
				.stream()
				.map(this::paraProdutoModelDTO)
				.collect(Collectors.toList());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		this.repository.deleteById(id);
	}
	
	public ProdutoModelDTO paraProdutoModelDTO(Produto produto) {
		return this.modelMapper.map(produto, ProdutoModelDTO.class);
	}

}
