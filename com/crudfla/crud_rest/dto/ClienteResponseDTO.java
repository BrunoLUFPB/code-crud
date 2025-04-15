package com.crudfla.crud_rest.dto;

public class ClienteResponseDTO {
	
	private Long id;
	
	private String nome;
	
	private String sexo;
	
	private Long idade;
	
	private Boolean fidelidade;
	
	private String cidade;
	
	private String cpf;
	
	private String numeroCelular;
	
	private String tipoConsultoria;
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	public Boolean getFidelidade() {
		return fidelidade;
	}

	public void setFidelidade(Boolean fidelidade) {
		this.fidelidade = fidelidade;
	}

	public String getTipoConsultoria() {
		return tipoConsultoria;
	}

	public void setTipoConsultoria(String tipoConsultoria) {
		this.tipoConsultoria = tipoConsultoria;
	}
	
	

}
