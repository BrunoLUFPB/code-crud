package com.crudfla.crud_rest.controle;

import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.modelo.Venda;
import com.crudfla.crud_rest.servico.VendaServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaControle {

    @Autowired
    private VendaServico vendaServico;

    // Endpoint para criar uma nova venda
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Cliente cliente = venda.getCliente();
        List<Consultoria> consultorias = venda.getConsultorias();
        String statusPagamento = venda.getStatusPagamento();
        String formaPagamento = venda.getFormaPagamento();
        Double desconto = venda.getDesconto();

        // Chama o serviço para criar a venda
        Venda novaVenda = vendaServico.criarVenda(cliente, consultorias, statusPagamento, formaPagamento, desconto);
        return ResponseEntity.status(201).body(novaVenda);
    }

    // Endpoint para obter todas as vendas de um cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Venda>> obterVendasDoCliente(@PathVariable Long clienteId) {
        List<Venda> vendas = vendaServico.obterVendasDoCliente(clienteId);
        return ResponseEntity.ok(vendas);
    }

    // Endpoint para obter todas as vendas de uma consultoria
    @GetMapping("/consultoria/{consultoriaId}")
    public ResponseEntity<List<Venda>> obterVendasDaConsultoria(@PathVariable Long consultoriaId) {
        List<Venda> vendas = vendaServico.obterVendasDaConsultoria(consultoriaId);
        return ResponseEntity.ok(vendas);
    }

    // Endpoint para atualizar o status de pagamento de uma venda
    @PutMapping("/{vendaId}/status-pagamento")
    public ResponseEntity<Venda> atualizarStatusPagamento(@PathVariable Long vendaId, @RequestParam String statusPagamento) {
        Venda vendaAtualizada = vendaServico.atualizarStatusPagamento(vendaId, statusPagamento);
        return ResponseEntity.ok(vendaAtualizada);
    }

    // Endpoint para calcular o valor total de uma venda
    @GetMapping("/{vendaId}/valor-total")
    public ResponseEntity<Double> calcularValorTotalVenda(@PathVariable Long vendaId) {
        Venda venda = vendaServico.obterVendasDoCliente(vendaId).get(0); // Método para obter venda (caso seja apenas uma venda por vez)
        Double valorTotal = vendaServico.calcularValorTotalVenda(venda.getConsultorias(), venda.getDesconto());
        return ResponseEntity.ok(valorTotal);
    }
}
