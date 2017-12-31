package com.jairosousa.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jairosousa.cursomc.domain.Categoria;
import com.jairosousa.cursomc.domain.Cidade;
import com.jairosousa.cursomc.domain.Cliente;
import com.jairosousa.cursomc.domain.Endereco;
import com.jairosousa.cursomc.domain.Estado;
import com.jairosousa.cursomc.domain.Produto;
import com.jairosousa.cursomc.domain.enums.TipoCliente;
import com.jairosousa.cursomc.repository.CategoriaRepository;
import com.jairosousa.cursomc.repository.CidadeRepository;
import com.jairosousa.cursomc.repository.ClienteRepository;
import com.jairosousa.cursomc.repository.EnderecoRepository;
import com.jairosousa.cursomc.repository.EstadoRepository;
import com.jairosousa.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Pará");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Belém", est1);
		Cidade c2 = new Cidade(null, "Ananindeua", est1);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@email.com", "35699207390", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32248965","9989247612"));
		
		Endereco e1 = new Endereco(null, "Rua Andradas", "230", "Berredos", "Icoaraci", "66600000", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "103", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
	}
}
