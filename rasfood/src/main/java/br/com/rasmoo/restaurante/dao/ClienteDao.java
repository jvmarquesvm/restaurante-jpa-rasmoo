package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Cliente;

public class ClienteDao {
	
	private EntityManager entityManager;
	
	public ClienteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}
	
	public Cliente consultarPorId(String cpf) {
		return this.entityManager.find(Cliente.class, cpf);
	}
	
	public void atualizar(Cliente cliente) {
		this.entityManager.merge(cliente);
	}
	
	public void excluir(Cliente cliente) {
		this.entityManager.remove(cliente);
	}
	
	public List<Cliente> consultarTodos() {
		try {
			String querySql = "select * from cliente";
			String queryJpql = "select c from Cliente c";
			return this.entityManager.createQuery(queryJpql, Cliente.class).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
