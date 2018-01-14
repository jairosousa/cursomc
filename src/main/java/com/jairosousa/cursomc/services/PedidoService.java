package com.jairosousa.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jairosousa.cursomc.domain.ItemPedido;
import com.jairosousa.cursomc.domain.PagamentoComBoleto;
import com.jairosousa.cursomc.domain.Pedido;
import com.jairosousa.cursomc.domain.enums.EstadoPagamento;
import com.jairosousa.cursomc.repository.ItemPedidoRepository;
import com.jairosousa.cursomc.repository.PagamentoRepository;
import com.jairosousa.cursomc.repository.PedidoRepository;
import com.jairosousa.cursomc.repository.ProdutoRepository;
import com.jairosousa.cursomc.services.exceptios.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BoletoService boletoService;

	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: " + Pedido.class.getName());
		}
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		// Setar o Pagemento
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);

		// Caso a escolha seja Pagamento com Boleto
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		// Salva Pedido
		obj = pedidoRepository.save(obj);
		// Salva Paagamento
		pagamentoRepository.save(obj.getPagamento());

		// Salvar os Itens do Pedido
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.save(obj.getItens());

		return obj;
	}

}
