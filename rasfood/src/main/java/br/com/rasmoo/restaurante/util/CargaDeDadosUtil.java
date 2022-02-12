package br.com.rasmoo.restaurante.util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

public class CargaDeDadosUtil {
	
	public static void cadastarCategorias(EntityManager entityManager) {
		Categoria entrada = new Categoria("Entradas");
		Categoria salada = new Categoria("Saladas");
		Categoria principal = new Categoria("Pratos Principais");

		CategoriaDao categoriaDao = new CategoriaDao(entityManager);

		categoriaDao.cadastrar(entrada);
		entityManager.flush();
		categoriaDao.cadastrar(salada);
		entityManager.flush();
		categoriaDao.cadastrar(principal);
		entityManager.flush();
		entityManager.clear();
	}
	
	public static void cadastrarProdutosCardapio(EntityManager entityManager) {
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodos();
        Cardapio moqueca = new Cardapio(null, "Moqueca", "Peixe branco, banana da terra, arroz e farofa",
                true, BigDecimal.valueOf(95.00), LocalDateTime.now(), categorias.get(2));
        Cardapio spaguetti = new Cardapio(null,"Spaguetti", "Spagatti ao molho de parmesão e cogumelos",
                true, BigDecimal.valueOf(68.00), LocalDateTime.now(), categorias.get(2));
        Cardapio bife = new Cardapio(null,"Bife", "Bife acebolado com arroz branco, farofa e batata frita",
                true, BigDecimal.valueOf(59.00), LocalDateTime.now(), categorias.get(2));
        Cardapio cordeiro = new Cardapio(null,"Cordeiro", "Cordeiro com risoto de funghi",
                true, BigDecimal.valueOf(88.00), LocalDateTime.now(), categorias.get(2));
        Cardapio burrata = new Cardapio(null,"Burrata", "Tomates queimados, rúcula e torrada",
                true, BigDecimal.valueOf(15.00), LocalDateTime.now(), categorias.get(0));
        Cardapio bruschetta = new Cardapio(null,"Bruschetta", "Tomate, mucarela e manjericao",
                true, BigDecimal.valueOf(20.00), LocalDateTime.now(), categorias.get(0));
        Cardapio scappeta = new Cardapio(null,"Scappeta", "Ragu de linguica e torradinhas",
                true, BigDecimal.valueOf(25.00), LocalDateTime.now(), categorias.get(0));
        Cardapio caprese = new Cardapio(null,"Caprese", "Mini rucula e mucarela",
                true, BigDecimal.valueOf(47.00), LocalDateTime.now(), categorias.get(1));
        Cardapio caesar = new Cardapio(null,"Caesar", "Salada de franco com molho ceasar",
                true, BigDecimal.valueOf(40.00), LocalDateTime.now(), categorias.get(1));
        Cardapio chevre = new Cardapio(null,"Chevre", "Mix de folhas, mostarda e mel",
                true, BigDecimal.valueOf(59.00), LocalDateTime.now(), categorias.get(1));

        cardapioDao.cadastrar(moqueca);
        cardapioDao.cadastrar(spaguetti);
        cardapioDao.cadastrar(bife);
        cardapioDao.cadastrar(cordeiro);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(bruschetta);
        cardapioDao.cadastrar(scappeta);
        cardapioDao.cadastrar(caprese);
        cardapioDao.cadastrar(caesar);
        cardapioDao.cadastrar(chevre);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
