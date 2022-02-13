package br.com.rasmoo.restaurante.dao;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensMaisVendidosResponse;

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
			//String querySql = "select * from ordem";
			String queryJpql = "select c from Ordem c";
			return this.entityManager.createQuery(queryJpql, Ordem.class).getResultList();
		} catch(Exception e) {
			return Collections.emptyList();
		}
	}
	
	//Consultar itens mais vendidos
	public List<ItensMaisVendidosResponse> consultarItensMaisVendidos(){
		//try {
			String queryJpql = "select new br.com.rasmoo.restaurante.vo.ItensMaisVendidosResponse( c.nome, sum(oc.quantidade))  "
							+ " from Ordem o "
							+ "   join OrdemCardapio oc on o.id = oc.cardapio"
							+ "   join Cardapio c       on oc.cardapio = c.id "
							//+ "   join oc.cardapio c "
							+ " group by c.nome "
							+ " order by sum(oc.quantidade) desc";
			return this.entityManager.createQuery(queryJpql, ItensMaisVendidosResponse.class).getResultList();
		//} catch(Exception e) {
		//	return Collections.emptyList();
		//}
	}
	
}
