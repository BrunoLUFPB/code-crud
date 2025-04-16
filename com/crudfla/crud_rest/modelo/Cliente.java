package com.crudfla.crud_rest.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sexo;
    private Long idade;

    @Column(nullable = false)
    private boolean fidelidade;

    private String cidade;

    @Column(nullable = false, unique = true)
    private String cpf;

    private String numeroCelular;  // Verifique se este campo está presente

    // Relacionamento One-to-Many com Venda
    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    // Construtor padrão
    public Cliente() {}

    // Construtor com parâmetros
    public Cliente(String nome, String sexo, Long idade, boolean fidelidade, String cidade, String cpf, String numeroCelular) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.fidelidade = fidelidade;
        this.cidade = cidade;
        this.cpf = cpf;
        this.numeroCelular = numeroCelular;
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

    // Getter e Setter para fidelidade (booleano)
    public boolean getFidelidade() {
        return fidelidade;
    }

    public void setFidelidade(boolean fidelidade) {
        this.fidelidade = fidelidade;
    }

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
        return numeroCelular;  // Verifique se o método getNumeroCelular() está presente
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
