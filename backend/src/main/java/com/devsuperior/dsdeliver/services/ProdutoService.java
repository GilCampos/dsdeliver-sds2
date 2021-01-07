package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.ProdutoDTO;
import com.devsuperior.dsdeliver.entidade.Produto;
import com.devsuperior.dsdeliver.repository.ProdutoRepositorio;



@Component
//instrutor anotou Service - mas nao funcionou
public class ProdutoService {
	
	@Autowired
	private ProdutoRepositorio repositorio;
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll() {
		List<Produto> list = repositorio.findAllByOrderByNomeAsc();
		return list.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}
}
