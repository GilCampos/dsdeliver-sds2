package com.devsuperior.dsdeliver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsdeliver.entidade.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{

		//verfica em spring data jpa documentation
		List<Produto> findAllByOrderByNomeAsc();
}
