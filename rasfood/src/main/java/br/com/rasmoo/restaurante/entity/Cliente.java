package br.com.rasmoo.restaurante.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	private String cpf;
	private String nome;
	//private String cep;
	@ManyToMany
	@JoinTable(name ="cliente_endereco",
			joinColumns  = @JoinColumn(name = "cliente_id"),
			inverseJoinColumns = @JoinColumn( name = "endereco_id")	)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Cliente() {
		super();
	}
	
	public Cliente(String cpf, String nome) {
		super();
		this.cpf = cpf;
		this.nome = nome;
	}
	
	public void addEndereco(Endereco endereco){
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(this);
	    endereco.setCliente(clientes);
	    this.enderecos.add(endereco);
	}
	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	//public String getCep() {
	//	return cep;
	//}
	//public void setCep(String cep) {
	//	this.cep = cep;
	//}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", nome=" + nome + ", endereco=" + enderecos + "]";
	}
	
}
