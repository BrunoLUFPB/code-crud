package com.crudfla.crud_rest.servico;

import com.crudfla.crud_rest.modelo.Venda;
import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.repositorio.VendaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaServico {

    @Autowired
    private VendaRepositorio vendaRepositorio;

    // Método para criar uma nova venda
    public Venda criarVenda(Cliente cliente, List<Consultoria> consultorias, String statusPagamento, String formaPagamento, Double desconto) {
        double valorTotal = calcularValorTotalVenda(consultorias, desconto);
        Venda novaVenda = new Venda(cliente, consultorias, statusPagamento, formaPagamento, desconto, valorTotal);
        return vendaRepositorio.save(novaVenda);
    }

    // Método para obter todas as vendas de um cliente
    public List<Venda> obterVendasDoCliente(Long clienteId) {
        return vendaRepositorio.findByClienteId(clienteId);
    }

    // Método para obter todas as vendas de uma consultoria
    public List<Venda> obterVendasDaConsultoria(Long consultoriaId) {
        return vendaRepositorio.findByConsultoriasId(consultoriaId);
    }

    // Método para atualizar o status de pagamento de uma venda
    public Venda atualizarStatusPagamento(Long vendaId, String statusPagamento) {
        Venda venda = vendaRepositorio.findById(vendaId).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        venda.setStatusPagamento(statusPagamento);
        return vendaRepositorio.save(venda);
    }

    // Método para calcular o valor total da venda
    public double calcularValorTotalVenda(List<Consultoria> consultorias, Double desconto) {
        double valorTotalConsultorias = consultorias.stream().mapToDouble(Consultoria::getPreco).sum();
        double valorComDesconto = valorTotalConsultorias - (valorTotalConsultorias * (desconto / 100));
        return valorComDesconto;
    }
}
