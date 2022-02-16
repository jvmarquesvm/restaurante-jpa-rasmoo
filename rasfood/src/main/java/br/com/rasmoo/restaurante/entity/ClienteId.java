package br.com.rasmoo.restaurante.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ClienteId implements Serializable {
	
	private static final long serialVersionUID = -5164182679253170918L;
	
	private String cpf;
	private String email;
	
	public ClienteId() {
		super();
	}
	
	public ClienteId(String cpf, String email) {
		super();
		this.cpf = cpf;
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "ClienteId [cpf=" + cpf + ", email=" + email + "]";
	}
	
}
