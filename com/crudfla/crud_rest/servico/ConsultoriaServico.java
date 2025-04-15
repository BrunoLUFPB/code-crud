package com.crudfla.crud_rest.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudfla.crud_rest.excecao.ClienteCpfJaExistenteException;
import com.crudfla.crud_rest.excecao.ClienteNotFoundException;
import com.crudfla.crud_rest.excecao.ConsultoriaNotFoundException;
import com.crudfla.crud_rest.excecao.ConsultoriaTipoJaExistenteException;
import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.repositorio.ClienteRepositorio;
import com.crudfla.crud_rest.repositorio.ConsultoriaRepositorio;

import jakarta.persistence.Column;

@Service
public class ConsultoriaServico {
    
    @Autowired
    private ConsultoriaRepositorio consultoriaRepositorio;
    
    public Consultoria gravarConsultoria(Consultoria consultoria) throws ConsultoriaTipoJaExistenteException {
        if (consultoriaRepositorio.existsByTipoConsultoria(consultoria.getTipoConsultoria())) {
            throw new ConsultoriaTipoJaExistenteException("Já existe uma consultoria do tipo: " + consultoria.getTipoConsultoria()); 
        }
        return consultoriaRepositorio.save(consultoria);
    }
    
    public List<Consultoria> buscarPorIdOuTipo(Long id, String tipoConsultoria) throws ConsultoriaNotFoundException {
        List<Consultoria> consultorias = new ArrayList<>();
        
        if (id != null) {
            Consultoria consultoria = consultoriaRepositorio.findById(id)
                .orElseThrow(() -> new ConsultoriaNotFoundException("Consultoria não encontrada."));
            
            if (tipoConsultoria != null && !tipoConsultoria.isBlank()) {
                if (!consultoria.getTipoConsultoria().equalsIgnoreCase(tipoConsultoria)) {
                    throw new ConsultoriaNotFoundException("Consultoria do ID " + id + " não é do tipo '" + tipoConsultoria + "'");
                }
            }
            consultorias.add(consultoria);
            
        } else if (tipoConsultoria != null && !tipoConsultoria.isBlank()) {
            consultorias = consultoriaRepositorio.findByTipoConsultoriaContainingIgnoreCase(tipoConsultoria);
        } else {
            consultorias = consultoriaRepositorio.findAll();
        }
        
        if (consultorias.isEmpty()) {
            throw new ConsultoriaNotFoundException("Nenhuma consultoria encontrada com os critérios informados");
        }
        
        return consultorias;
    }
    
    public Consultoria alterarConsultoria(Long id, Consultoria consultoria) 
            throws ConsultoriaNotFoundException, ConsultoriaTipoJaExistenteException {
        
        Consultoria consultoriaGravada = buscarPorIdOuTipo(id, null).get(0);
        
        if (consultoria.getTipoConsultoria() == null || consultoria.getTipoConsultoria().isBlank()) {
            throw new IllegalArgumentException("Tipo da consultoria é obrigatório");
        }
        
        boolean tipoAlterado = !Objects.equals(consultoriaGravada.getTipoConsultoria(), consultoria.getTipoConsultoria());
        
        if (tipoAlterado) {
            boolean tipoExistente = consultoriaRepositorio.existsByTipoConsultoriaAndIdNot(
                consultoria.getTipoConsultoria(), 
                id
            );
            if (tipoExistente) {
                throw new ConsultoriaTipoJaExistenteException("Tipo de consultoria já está em uso: " + consultoria.getTipoConsultoria());
            }
        }
        
        // Atualização dos campos
        consultoriaGravada.setDescricao(consultoria.getDescricao());
        consultoriaGravada.setDuracaoHoras(consultoria.getDuracaoHoras());
        consultoriaGravada.setValor(consultoria.getValor());
        consultoriaGravada.setTipoConsultoria(consultoria.getTipoConsultoria());
        
        return consultoriaRepositorio.save(consultoriaGravada);
    }

    public void apagarConsultoria(Long id) throws ConsultoriaNotFoundException {
        Consultoria consultoria = consultoriaRepositorio.findById(id)
            .orElseThrow(() -> new ConsultoriaNotFoundException("Consultoria não encontrada para exclusão"));
        consultoriaRepositorio.delete(consultoria);
    }
}
