package com.demetrio.cadastroprodutosapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demetrio.cadastroprodutosapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	Produto save(Optional<Produto> produtoSalvo);

	@Query(value="select * from produto where id = ?", nativeQuery = true)
	Produto buscarProduto(Long id);
}
