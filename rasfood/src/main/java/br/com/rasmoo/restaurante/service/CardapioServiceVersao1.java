package br.com.rasmoo.restaurante.service;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import br.com.rasmoo.restaurante.entity.Cardapio;

public class CardapioServiceVersao1 {
	public static void main(String[] args) {
		
		Cardapio risoto = new Cardapio(); 
		risoto.setNome("Risoto de frutos do mar");
		risoto.setDescricao("Risoto acompanhado de lula, polvo e mariscos");
		risoto.setDisponivel(true);
		risoto.setValor(BigDecimal.valueOf(88.50));
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rasfood");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(risoto);
		
		entityManager.getTransaction().commit();
		entityManager.close();

	}
}
