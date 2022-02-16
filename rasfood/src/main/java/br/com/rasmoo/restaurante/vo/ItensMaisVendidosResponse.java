package br.com.rasmoo.restaurante.vo;

public class ItensMaisVendidosResponse {
	
	private String nome;
	private Long quantidade;
	
	public ItensMaisVendidosResponse(String nome, Long quantidade) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		return "ItensMaisVendidosResponse [nome=" + nome + ", quantidade=" + quantidade + "]";
	}
	

}
