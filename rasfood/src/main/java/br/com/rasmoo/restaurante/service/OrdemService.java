package br.com.rasmoo.restaurante.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.ClienteId;
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
        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);
		
		ClienteDao clienteDao = new ClienteDao(entityManager);
		EnderecoDao enderecoDao = new EnderecoDao(entityManager);
		
		Endereco endereco = new Endereco("08776876", "Rua Inácio", "980", "SP","Santo André");
		Cliente cliente = new Cliente("78956873435", "joao@email.com", "João Victor");
		cliente.addEndereco(endereco);
		
		enderecoDao.cadastrar(endereco);
		clienteDao.cadastrar(cliente);
		//entityManager.clear();
		//entityManager.flush();
		
		clienteDao.consultarTodos().forEach(item -> System.out.println(item));
		
		OrdemDao ordemDao = new OrdemDao(entityManager);
		CardapioDao cardapioDao = new CardapioDao(entityManager); 
		
		cardapioDao.consultarTodos().forEach(item -> System.out.println(item));

		Ordem ordem = new Ordem(clienteDao.consultarPorId(new ClienteId("78956873435","joao@email.com")));
		ordem.addOrdensCardapio(new OrdemCardapio( cardapioDao.consultarPorId(8L), 11));
		ordem.addOrdensCardapio(new OrdemCardapio( cardapioDao.consultarPorId(4L), 1));
		//ordem.addOrdensCardapio(new OrdemCardapio( cardapioDao.consultarPorId(5L), 35));
		ordemDao.cadastrar(ordem);
		entityManager.flush();
		entityManager.clear();

		//ordemDao.consultarTodos().forEach(item -> System.out.println(item));
		
		//Ordem ordemDaoResponse = ordemDao.consultarPorId(2L);
		//Ordem ordemDaoResponse = ordemDao.joinFetchCliente(1L);
		
		//System.out.println( clienteDao.consultarPorNome("Costa") );
		
		//System.out.println(  clienteDao.consultarClientes("Sao Paulo", "augusta", "SP") );
		//System.out.println(  enderecoDao.consultarClientes("Sao Paulo", "augusta", "SP") );
		//System.out.println(  enderecoDao.consultarClientes("Sao Paulo", null, "SP") );
		System.out.println(  enderecoDao.consultarClientesUsandoCriteria("Sao Paulo", null, "sP") );
		entityManager.getTransaction().commit();
		entityManager.close();

		//System.out.println(ordemDaoResponse.getCliente().getNome());

		//System.out.println(	ordemDao.consultarItensMaisVendidos());
		
	}
}
