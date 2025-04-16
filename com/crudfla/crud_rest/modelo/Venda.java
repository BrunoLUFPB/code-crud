package com.crudfla.crud_rest.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToMany
    @JoinTable(
        name = "venda_consultoria", 
        joinColumns = @JoinColumn(name = "venda_id"), 
        inverseJoinColumns = @JoinColumn(name = "consultoria_id")
    )
    private List<Consultoria> consultorias;

    private String statusPagamento;
    private String formaPagamento;
    private Double desconto;
    private Double valorTotal;

    // Construtor padrão
    public Venda() {}

    // Construtor com parâmetros
    public Venda(Cliente cliente, List<Consultoria> consultorias, String statusPagamento, String formaPagamento, Double desconto, Double valorTotal) {
        this.cliente = cliente;
        this.consultorias = consultorias;
        this.statusPagamento = statusPagamento;
        this.formaPagamento = formaPagamento;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Consultoria> getConsultorias() {
        return consultorias;
    }

    public void setConsultorias(List<Consultoria> consultorias) {
        this.consultorias = consultorias;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
