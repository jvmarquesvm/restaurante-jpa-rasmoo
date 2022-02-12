package br.com.rasmoo.restaurante.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;
import br.com.rasmoo.restaurante.util.JPAUtil;

public class CardapioServiceVersao2 {
	
	public static void main(String[] args) {
		EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
		cadastrarProdutoCardapio(entityManager, cadastrarCategoria(entityManager));
	}
	
	private static Categoria cadastrarCategoria(EntityManager entityManager) {
		CategoriaDao categoriaDao = new CategoriaDao(entityManager);
		Categoria pratoPrincipal = new Categoria("Prato Principal");
		/*
		 * JdbcSQLIntegrityConstraintViolationException
		 * Referential integrity constraint violation: "FKH86CANK8P6ATSVMKBCFSBI5AH: PUBLIC.CARDAPIO FOREIGN KEY
		 * */
		//pratoPrincipal.setId(1L);
		entityManager.getTransaction().begin();
		
		/* org.hibernate.TransientPropertyValueException
		 * object references an unsaved transient instance - save the transient instance before flushing
		 * Estado Transiente - salve antes de da sincronização
		 * Para salvar cardapio é preciso ter categoria salva no banco 
		 * */
		categoriaDao.cadastrar(pratoPrincipal);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return pratoPrincipal;
	}
	
	private static void cadastrarProdutoCardapio(EntityManager entityManager, Categoria categoria) {
		Cardapio risoto = new Cardapio();
		risoto.setNome("Risoto de frutos do mar");
		risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
		risoto.setDisponivel(true);
		risoto.setValor(BigDecimal.valueOf(88.50));
		risoto.setCategoria(categoria);

		Cardapio salmao = new Cardapio();
		salmao.setNome("Salmão ao molho de maracujá");
		salmao.setDescricao("Salmão ao molho de maracujá");
		salmao.setDisponivel(true);
		salmao.setValor(BigDecimal.valueOf(60.00));
		salmao.setCategoria(categoria);

		CardapioDao cardapioDao = new CardapioDao(entityManager);
		entityManager.getTransaction().begin();

		cardapioDao.cadastrar(risoto);
		entityManager.flush();
		cardapioDao.cadastrar(salmao);
		entityManager.flush();
		//System.out.println("O prato consultado foi: " + cardapioDao.consultarPorId(1L));
		//System.out.println("O prato consultado foi: " + cardapioDao.consultarPorId(2L));
		cardapioDao.consultarTodos().forEach(
				pratos -> System.out.println("O prato consultado foi: " + pratos)); 

		entityManager.getTransaction().commit();
		entityManager.close(); 
	}
	
}
