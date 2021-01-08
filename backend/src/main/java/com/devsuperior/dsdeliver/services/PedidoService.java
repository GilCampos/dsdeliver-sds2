package com.devsuperior.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsdeliver.dto.PedidoDTO;
import com.devsuperior.dsdeliver.dto.ProdutoDTO;
import com.devsuperior.dsdeliver.entidade.Pedido;
import com.devsuperior.dsdeliver.entidade.Produto;
import com.devsuperior.dsdeliver.entidade.StatusPedido;
import com.devsuperior.dsdeliver.repository.PedidoRepositorio;
import com.devsuperior.dsdeliver.repository.ProdutoRepositorio;

@Component
//instrutor anotou Service - mas nao funcionou
public class PedidoService {
	
	@Autowired
	private PedidoRepositorio repositorio;
	
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> list = repositorio.findPedidosWithProdutos();
		return list.stream().map(x -> new PedidoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public PedidoDTO insert(PedidoDTO dto) {
		Pedido pedido = new Pedido(null, dto.getEndereco(), dto.getLatitude(), dto.getLongitude(),
				Instant.now(), StatusPedido.PENDENTE);
		for(ProdutoDTO p : dto.getProdutos()) {
			Produto produto = produtoRepositorio.getOne(p.getId());
			pedido.getProdutos().add(produto);
		}
		pedido = repositorio.save(pedido);
		return new PedidoDTO(pedido);
	}
	
	@Transactional
	public PedidoDTO setDelivered(Long id) {
		Pedido pedido = repositorio.getOne(id);
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido = repositorio.save(pedido);
		return new PedidoDTO(pedido);
	}
	
}
