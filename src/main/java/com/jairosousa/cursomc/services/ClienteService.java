package com.jairosousa.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairosousa.cursomc.domain.Cliente;
import com.jairosousa.cursomc.repository.ClienteRepository;
import com.jairosousa.cursomc.services.exceptios.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", tipo: " + Cliente.class.getName());
		}
		return obj;
	}

}
