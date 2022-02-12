package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Ordem;

public class OrdemDao {
	
	private EntityManager entityManager;
	
	public OrdemDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Ordem ordem) {
		this.entityManager.persist(ordem);
	}
	
	public Ordem consultarPorId(Long id) {
		return this.entityManager.find(Ordem.class, id);
	}
	
	public void atualizar(Ordem ordem) {
		this.entityManager.merge(ordem);
	}
	
	public void excluir(Ordem ordem) {
		this.entityManager.remove(ordem);
	}
	
	public List<Ordem> consultarTodos() {
		try {
			String querySql = "select * from ordem";
			String queryJpql = "select c from Ordem c";
			return this.entityManager.createQuery(queryJpql, Ordem.class).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
}
