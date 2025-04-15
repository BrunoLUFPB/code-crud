package com.crudfla.crud_rest.repositorio;

import com.crudfla.crud_rest.modelo.Consultoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ConsultoriaRepositorio extends JpaRepository<Consultoria, Long> {

    // Verifica se já existe uma consultoria com o mesmo tipo
    boolean existsByTipoConsultoria(String tipoConsultoria);

    // Verifica se outro registro tem o mesmo tipo (exceto o atual)
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
           "FROM Consultoria c " +
           "WHERE c.tipoConsultoria = :tipo AND c.id <> :id")
    boolean existsByTipoConsultoriaAndIdNot(
        @Param("tipo") String tipoConsultoria, 
        @Param("id") Long id
    );

    // Busca por correspondência exata (case-sensitive)
    List<Consultoria> findByTipoConsultoriaContaining(String tipoConsultoria);

    // Busca por correspondência parcial (case-insensitive)
    List<Consultoria> findByTipoConsultoriaContainingIgnoreCase(String tipoConsultoria);
}