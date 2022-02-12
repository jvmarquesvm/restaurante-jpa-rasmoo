package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Endereco;

public class EnderecoDao {
	
	private EntityManager entityManager;
	
	public EnderecoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Endereco endereco) {
		this.entityManager.persist(endereco);
	}
	
	public Endereco consultarPorId(Long id) {
		return this.entityManager.find(Endereco.class, id);
	}
	
	public void atualizar(Endereco endereco) {
		this.entityManager.merge(endereco);
	}
	
	public void excluir(Endereco endereco) {
		this.entityManager.remove(endereco);
	}
	
	public List<Endereco> consultarTodos() {
		try {
			String querySql = "select * from endereco";
			String queryJpql = "select e from Endereco e";
			return this.entityManager.createQuery(queryJpql, Endereco.class).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
