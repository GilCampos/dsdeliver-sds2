package com.devsuperior.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.PedidoDTO;
import com.devsuperior.dsdeliver.entidade.Pedido;
import com.devsuperior.dsdeliver.repository.PedidoRepositorio;

@Component
//instrutor anotou Service - mas nao funcionou
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repositorio;
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> list = repositorio.findPedidosWithProdutos();
		return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}
}
