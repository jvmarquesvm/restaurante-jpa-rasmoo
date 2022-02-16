package br.com.rasmoo.restaurante.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
	
	//@Id
	//private String cpf;
	@EmbeddedId
	private ClienteId clienteId;
	
	private String nome;
	//private String cep;
	
	@ManyToMany
	//@JoinTable(name ="cliente_endereco", 
	//	joinColumns = @JoinColumn(name = "cliente_id"),
	//		inverseJoinColumns = @JoinColumn( name = "endereco_id")	)
	@JoinColumns({
		@JoinColumn(name = "cpf", insertable = false, updatable = false),
		@JoinColumn(name = "email", insertable = false, updatable = false),
	})
	private List<Endereco> enderecos = new ArrayList<>();
	
	@Embedded
	private Contato contato;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String cpf, String email, String nome) {
		super();
		this.clienteId = new ClienteId(cpf, email);
		this.nome = nome;
	}
	
	public void addEndereco(Endereco endereco){
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(this);
	    endereco.setCliente(clientes);
	    this.enderecos.add(endereco);
	}
	
	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public String getCpf() {
		//return cpf;
		return this.clienteId.getCpf();
	}
	public void setCpf(String cpf) {
		//this.cpf = cpf;
		this.clienteId.setCpf(cpf);
	}
	public String getEmail() {
		return this.clienteId.getEmail();
	}
	public void setEmail(String email) {
		this.clienteId.setEmail(email);
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
	public String toString() {
		return "Cliente [cpf=" + this.clienteId.getCpf() + ", email=" + this.clienteId.getEmail() + ", nome=" + nome + ", enderecos=" + enderecos + ", contato=" + contato + "]";
	}

}
