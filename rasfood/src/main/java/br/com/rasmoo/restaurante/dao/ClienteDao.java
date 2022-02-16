package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.vo.ClienteVo;

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
	
	public List<Cliente> consultarPorNome(String nome) {
		try {
			String queryJpql = "select c from Cliente c where lower(c.nome) like lower(:nome)";
			return this.entityManager.createQuery(queryJpql, Cliente.class).setParameter("nome", "%" + nome + "%").getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
	public List<ClienteVo> consultarClientes(String estado, String rua, String cidade) {
		try {
			String queryJpql = "select new br.com.rasmoo.restaurante.vo.ClienteVo(c.cpf, c.nome) from Cliente c join c.enderecos e where  upper(e.estado) = upper(:estado) and upper(e.cidade) = upper(:cidade) and upper(e.rua) = upper(:rua)";
			
			return this.entityManager.createQuery(queryJpql, ClienteVo.class)
					.setParameter("estado", estado)
					.setParameter("rua", rua)
					.setParameter("cidade", cidade)
					.getResultList();
			
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
