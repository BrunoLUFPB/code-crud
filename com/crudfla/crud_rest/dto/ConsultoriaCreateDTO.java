package com.crudfla.crud_rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ConsultoriaCreateDTO {

    @NotBlank(message = "O nome da consultoria é obrigatório.")
    private String nome;

    @NotNull(message = "O preço da consultoria é obrigatório.")
    @Positive(message = "O preço da consultoria deve ser um valor positivo.")
    private Double preco;

    @NotNull(message = "A quantidade em estoque é obrigatória.")
    @Positive(message = "O estoque deve ser maior que zero.")
    private Integer estoque;

    @NotNull(message = "O consultor_id é obrigatório.")
    private Long consultor_id;

    @NotBlank(message = "A categoria da consultoria é obrigatória.")
    private String categoria;

    @NotBlank(message = "A descrição da consultoria é obrigatória.")
    private String descricao;

    // Construtor padrão
    public ConsultoriaCreateDTO() {}

    // Construtor com parâmetros
    public ConsultoriaCreateDTO(String nome, Double preco, Integer estoque, Long consultor_id, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.consultor_id = consultor_id;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Long getConsultor_id() {
        return consultor_id;
    }

    public void setConsultor_id(Long consultor_id) {
        this.consultor_id = consultor_id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
