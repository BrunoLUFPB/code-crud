package com.crudfla.crud_rest.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Consultoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double preco;
    private Integer estoque;
    private Long consultor_id;
    private String categoria;
    private String descricao;

    @ManyToMany(mappedBy = "consultorias")  // Relacionamento Many-to-Many com Venda
    private List<Venda> vendas;

    // Construtor padrão
    public Consultoria() {}

    // Construtor com parâmetros
    public Consultoria(String nome, Double preco, Integer estoque, Long consultor_id, String categoria, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
        this.consultor_id = consultor_id;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    // Getters e Setters
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

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
