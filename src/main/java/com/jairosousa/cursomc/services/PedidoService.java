package com.jairosousa.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairosousa.cursomc.domain.Pedido;
import com.jairosousa.cursomc.repository.PedidoRepository;
import com.jairosousa.cursomc.services.exceptios.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", tipo: " + Pedido.class.getName());
		}
		return obj;
	}

}
