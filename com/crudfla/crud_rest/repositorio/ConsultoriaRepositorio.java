package com.crudfla.crud_rest.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.modelo.Venda;
import java.util.List; 

@Repository
public interface ConsultoriaRepositorio extends JpaRepository<Consultoria, Long> {
	
	// Método para buscar todas as vendas associadas a uma consultoria
    List<Venda> findByVendasId(Long consultoriaId);

    // Método para verificar se uma consultoria com o nome já existe
    boolean existsByNome(String nome);

    // Método para encontrar uma consultoria pelo nome
    Consultoria findByNome(String nome);
}
