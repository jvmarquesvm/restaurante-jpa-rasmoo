package br.com.rasmoo.restaurante.dao;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.entity.Cardapio;

public class CardapioDao {
	
	private EntityManager entityManager;
	
	public CardapioDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Cardapio prato) {
		this.entityManager.persist(prato);
	}
	
	public Cardapio consultarPorId(Long id) {
		return this.entityManager.find(Cardapio.class, id);
	}
	
	public void atualizar(Cardapio prato) {
		this.entityManager.merge(prato);
	}
	
	public void excluir(Cardapio prato) {
		this.entityManager.remove(prato);
	}
	
	public List<Cardapio> consultarTodos() {
		try {
			String querySql = "select * from cardapio";
			String queryJpql = "select c from Cardapio c";
			return this.entityManager.createQuery(queryJpql, Cardapio.class).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
	public List<Cardapio> consultarPorValor(BigDecimal valor) {
		try {
			String queryJpql = "select c from Cardapio c where c.valor = :valor";
			return this.entityManager
					       .createQuery(queryJpql, Cardapio.class)
					           .setParameter("valor", valor).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();		
		}
	}
	
	public Cardapio consultarPorNome(String nome) {
		try {
			String queryJpql = "select c from Cardapio c where lower(c.nome) = lower(:nome)";
			return this.entityManager
				.createQuery(queryJpql, Cardapio.class)
				.setParameter("nome", nome).getSingleResult();
		} catch(Exception e) {
			return null;			
		}
	}

}
