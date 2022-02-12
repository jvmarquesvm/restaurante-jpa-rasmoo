package br.com.rasmoo.restaurante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordem")
public class Ordem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal = BigDecimal.ZERO;
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	@ManyToOne
	private Cliente cliente;
	
	//Forma automática - 1 forma
	//@ManyToMany
	//@JoinTable(name = "ordem_cardapio",
	//           joinColumns = @JoinColumn(name = "ordem_id"), 
	//           inverseJoinColumns = @JoinColumn(name = "cardapio_id") )
	//private List<Cardapio> cardapio;
	
	//Forma manual - 2 forma
	@OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL) //indicando que se trata de um relacionamento bidirecional
	private List<OrdemCardapio> ordemCardapio = new ArrayList<>(); //Sempre instanciar
	
	public void addOrdensCardapio(OrdemCardapio ordemCardapio) {
		ordemCardapio.setOrdem(this);
		valorTotal = valorTotal.add(ordemCardapio.getValorPago()
				                       .multiply(BigDecimal.valueOf(ordemCardapio.getQuantidade())));
		this.ordemCardapio.add(ordemCardapio);
	}
	
	public Ordem() {
		super();
	}
	
	public Ordem( Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	
	public List<OrdemCardapio> getOrdemCardapio() {
		return ordemCardapio;
	}

	public void setOrdemCardapio(List<OrdemCardapio> ordemCardapio) {
		this.ordemCardapio = ordemCardapio;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordem other = (Ordem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ordem [id=" + id + ", valorTotal=" + valorTotal + ", dataCriacao=" + dataCriacao + ", cliente="
				+ cliente + ", ordemCardapio=" + ordemCardapio + "]";
	}


	
}
