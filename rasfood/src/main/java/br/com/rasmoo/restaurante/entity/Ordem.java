package br.com.rasmoo.restaurante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ordem")
public class Ordem {
	
	@Id
	private Long id;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	@ManyToOne
	private Cliente cliente;
	
	public Ordem() {
		super();
	}
	
	public Ordem(BigDecimal valorTotal, LocalDateTime dataCriacao, Cliente cliente) {
		super();
		this.valorTotal = valorTotal;
		this.dataCriacao = dataCriacao;
		this.cliente = cliente;
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
				+ cliente + "]";
	}
	
}
