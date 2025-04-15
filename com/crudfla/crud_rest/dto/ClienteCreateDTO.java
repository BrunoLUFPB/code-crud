package com.crudfla.crud_rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClienteCreateDTO {
	
	
	@NotBlank(message = "O nome deve ser informado")
	@Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres")
	private String nome;
	
	@NotBlank(message = "O sexo deve ser informado")
	@Pattern(regexp = "^(Masculino|Feminino|Outro)$", message = "O sexo deve ser 'Masculino', 'Feminino' ou 'Outro'")
	private String sexo;
	
	@Min(value = 18, message = "A idade deve ser no mínimo 18 anos")
	@NotNull(message = "Idade deve ser informado")
	private Long idade;
	
	@NotNull(message = "A fidelidade deve ser informada")
	private Boolean fidelidade;
	
	@NotBlank(message = "A cidade deve ser informada")
	private String cidade;
	
	@NotBlank(message = "CPF é obrigatório")
	@Pattern(regexp = "^(\\d{11})$", message = "O CPF deve conter 11 dígitos")
	private String cpf;
	
	@NotBlank(message = "Número é obrigatório")
	@Pattern(regexp = "^(\\d{11})$", message = "O número de celular deve conter 11 dígitos")
	private String numeroCelular;

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
	

}
