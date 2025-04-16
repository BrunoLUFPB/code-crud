package com.crudfla.crud_rest.repositorio;

import com.crudfla.crud_rest.modelo.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long> {

    // Método para buscar todas as vendas de um cliente específico
    List<Venda> findByClienteId(Long clienteId);

    // Método para buscar todas as vendas associadas a uma consultoria específica
    List<Venda> findByConsultoriasId(Long consultoriaId);
}
