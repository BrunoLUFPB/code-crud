package com.crudfla.crud_rest.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crudfla.crud_rest.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
	
	 boolean existsByCpf(String cpf);
	 
	 @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.cpf = :cpf AND c.id <> :id")
	    boolean existsByCpfAndIdNot(@Param("cpf") String cpf, @Param("id") Long id);

    List<Cliente> findByNomeContaining(String nome);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
