package br.com.rasmoo.restaurante.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Categoria;

public class CategoriaDao {
	
	private EntityManager entityManager;

	public CategoriaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Categoria pratoPrincipal) {
		this.entityManager.persist(pratoPrincipal);
	}
	
	public List<Categoria> consultarTodos() {
		String queryJpql = "select c from Categoria c";
		return this.entityManager.createQuery(queryJpql, Categoria.class).getResultList();
	}
	
}
