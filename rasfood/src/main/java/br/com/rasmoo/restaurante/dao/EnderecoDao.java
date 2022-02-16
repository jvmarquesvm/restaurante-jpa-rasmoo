package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.vo.ClienteVo;

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
	
	public List<ClienteVo> consultarClientes(String estado, String rua, String cidade) {
		try {
			//String queryJpql = "select new br.com.rasmoo.restaurante.vo.ClienteVo(c.cpf, c.nome) from Endereco e join e.cliente c where upper(e.estado) = upper(:estado) and upper(e.cidade) = upper(:cidade) and upper(e.rua) = upper(:rua)";
			String queryJpql = "select new br.com.rasmoo.restaurante.vo.ClienteVo(c.cpf, c.nome) from Endereco e join e.cliente c where 1 = 1";
			
			if(Objects.nonNull(estado)) {
				queryJpql = queryJpql.concat( " and upper(e.estado) = upper(:estado) ");
			}
			if (Objects.nonNull(cidade)) {
				queryJpql = queryJpql.concat( " and upper(e.cidade) = upper(:cidade) ");
			}
			if (Objects.nonNull(rua)) {
				queryJpql = queryJpql.concat( " and upper(e.rua) = upper(:rua) ");
			}
			
			/*return this.entityManager.createQuery(queryJpql, ClienteVo.class)
					.setParameter("estado", estado)
					.setParameter("rua", rua)
					.setParameter("cidade", cidade)
					.getResultList();*/
			
			TypedQuery<ClienteVo> createQuery = this.entityManager.createQuery(queryJpql, ClienteVo.class);
			
			if(Objects.nonNull(estado)) {
				createQuery.setParameter("estado", estado);
			}
			if (Objects.nonNull(cidade)) {
				createQuery.setParameter("cidade", cidade);
			}
			if (Objects.nonNull(rua)) {
				createQuery.setParameter("rua", rua);
			}
			
			return createQuery.getResultList();
			
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
	public List<ClienteVo> consultarClientesUsandoCriteria(String estado, String rua, String cidade) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClienteVo> createQuery = builder.createQuery(ClienteVo.class);
		Root<Endereco> root = createQuery.from(Endereco.class);
		Join<Endereco, Cliente> join = root.join("cliente");
		
		createQuery.multiselect( join.get("cpf"), join.get("nome") );
		Predicate predicate = builder.and();
		
		if(Objects.nonNull(estado)) {
			predicate = builder.and(predicate, builder.equal(builder.upper( root.get("estado") ), estado.toUpperCase() ));
		}
		if(Objects.nonNull(cidade)) {
			predicate = builder.and(predicate, builder.equal(builder.upper( root.get("cidade") ), cidade.toUpperCase() ));
		}
		if(Objects.nonNull(rua)) {
			predicate = builder.and(predicate, builder.equal(builder.upper( root.get("rua") ), rua.toUpperCase()));
		}
		
		createQuery.where(predicate);
		return entityManager.createQuery(createQuery).getResultList();
	}
	
}
