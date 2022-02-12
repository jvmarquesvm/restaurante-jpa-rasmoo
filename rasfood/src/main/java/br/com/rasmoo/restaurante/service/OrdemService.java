package br.com.rasmoo.restaurante.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.entity.OrdemCardapio;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

public class OrdemService {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
		entityManager.getTransaction().begin();
		CargaDeDadosUtil.cadastarCategorias(entityManager);
		CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
		
		CardapioDao cardapioDao = new CardapioDao(entityManager);
		ClienteDao clienteDao = new ClienteDao(entityManager);
		OrdemDao ordemDao = new OrdemDao(entityManager);
		EnderecoDao enderecoDao = new EnderecoDao(entityManager);
		Endereco endereco = new Endereco("08776876", "Rua Inácio", "980", "SP","Santo André");
		
		Cliente cliente = new Cliente("78956873435", "João Victor");
		List<Endereco> enderecos = new ArrayList<Endereco>();
		enderecos.add(endereco);
		cliente.setEnderecos(enderecos);
		
		Ordem ordem = new Ordem(cliente);
		ordem.addOrdensCardapio(new OrdemCardapio( cardapioDao.consultarPorId(1L), 2));
		
		enderecoDao.cadastrar(endereco);
		clienteDao.cadastrar(cliente);
		ordemDao.cadastrar(ordem);
		System.out.println(ordem);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}
