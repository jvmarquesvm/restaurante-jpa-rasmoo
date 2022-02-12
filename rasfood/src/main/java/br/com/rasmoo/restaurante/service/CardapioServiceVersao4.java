package br.com.rasmoo.restaurante.service;

import java.math.BigDecimal;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.OrdemDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

public class CardapioServiceVersao4 {

	  public static void main(String[] args) {
	        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
	        entityManager.getTransaction().begin();
	        CargaDeDadosUtil.cadastarCategorias(entityManager);
	        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
	        CargaDeDadosUtil.cadastrarClientes(entityManager);
	        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);
	        OrdemDao ordemDao = new OrdemDao(entityManager);
	        
	        //ordemDao.consultarTodos().forEach(item -> System.out.println(item));

	        ordemDao.consultarItensMaisVendidos().forEach(item->System.out.println("Item: "+item[0]+"\t-\tQuantidade: "+item[1]));
	        entityManager.getTransaction().commit();
	        entityManager.close();
	    }

}
