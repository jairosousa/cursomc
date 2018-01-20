package com.jairosousa.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jairosousa.cursomc.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeService service;

	
}
