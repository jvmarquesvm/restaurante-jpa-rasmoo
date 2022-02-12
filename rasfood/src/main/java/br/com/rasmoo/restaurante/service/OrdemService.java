package br.com.rasmoo.restaurante.service;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.ClienteDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.entity.Cliente;
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
		
		Cliente cliente = new Cliente("78956873435", "João Victor", "094088000");
		Ordem ordem = new Ordem(cliente);
		ordem.addOrdensCardapio(new OrdemCardapio(ordem, cardapioDao.consultarPorId(1L), 2));
		
		clienteDao.cadastrar(cliente);
		ordemDao.cadastrar(ordem);
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
}
