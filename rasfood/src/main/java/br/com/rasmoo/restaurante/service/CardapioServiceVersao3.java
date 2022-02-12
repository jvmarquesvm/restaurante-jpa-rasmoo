package br.com.rasmoo.restaurante.service;


import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

public class CardapioServiceVersao3 {

	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
		entityManager.getTransaction().begin();
		CargaDeDadosUtil.cadastarCategorias(entityManager);
		CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
		
		CardapioDao cardapioDao = new CardapioDao(entityManager);
		//System.out.println("Lista de produtos por valor: " + 
		//                         cardapioDao.consultarPorValor(BigDecimal.valueOf(59.00)));
		System.out.println("O produto pesquisado foi: " + 
		                         cardapioDao.consultarPorNome("bruschetta"));		
		
		entityManager.close();
	}

}
