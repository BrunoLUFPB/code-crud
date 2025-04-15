package com.crudfla.crud_rest.dto;

public class ConsultoriaResponseDTO {
	
	private Long id;
    private String tipoConsultoria;  
    private String descricao;
    private Double duracaoHoras;
    private Long valor;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoConsultoria() {
		return tipoConsultoria;
	}
	public void setTipoConsultoria(String tipoConsultoria) {
		this.tipoConsultoria = tipoConsultoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getDuracaoHoras() {
		return duracaoHoras;
	}
	public void setDuracaoHoras(Double duracaoHoras) {
		this.duracaoHoras = duracaoHoras;
	}
	public Long getValor() {
		return valor;
	}
	public void setValor(Long valor) {
		this.valor = valor;
	}

}
