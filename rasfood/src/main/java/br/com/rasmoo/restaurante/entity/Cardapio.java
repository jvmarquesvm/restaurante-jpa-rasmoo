package br.com.rasmoo.restaurante.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cardapio")
public class Cardapio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private Boolean disponivel;
	private BigDecimal valor;
	@ManyToOne
	private Categoria categoria;
	
	@Column(name = "data_de_registro")
	private LocalDateTime dataDeRegistro = LocalDateTime.now();

	public Cardapio(Long id, String nome, String descricao, Boolean disponivel, BigDecimal valor,
			LocalDateTime dataDeRegistro, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.disponivel = disponivel;
		this.valor = valor;
		this.dataDeRegistro = dataDeRegistro;
		this.categoria = categoria;
	}

	public Cardapio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(LocalDateTime dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Cardapio other = (Cardapio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cardapio [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", disponivel=" + disponivel
				+ ", valor=" + valor + ", categoria=" + categoria + ", dataDeRegistro=" + dataDeRegistro + "]";
	}

}
