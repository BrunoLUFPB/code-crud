package com.crudfla.crud_rest.controle;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crudfla.crud_rest.dto.ClienteCreateDTO;
import com.crudfla.crud_rest.dto.ClienteResponseDTO;
import com.crudfla.crud_rest.dto.ConsultoriaCreateDTO;
import com.crudfla.crud_rest.dto.ConsultoriaResponseDTO;
import com.crudfla.crud_rest.dto.mapper.ClienteMapper;
import com.crudfla.crud_rest.dto.mapper.ConsultoriaMapper;
import com.crudfla.crud_rest.excecao.ClienteCpfJaExistenteException;
import com.crudfla.crud_rest.excecao.ClienteNotFoundException;
import com.crudfla.crud_rest.excecao.ConsultoriaNotFoundException;
import com.crudfla.crud_rest.excecao.ConsultoriaTipoJaExistenteException;
import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.servico.ConsultoriaServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultorias")
public class ConsultoriaControle {
    
    @Autowired
    private ConsultoriaServico consultoriaServico;
    
    @Autowired
    private ConsultoriaMapper consultoriaMapper;
    
    @PostMapping
    public ResponseEntity<Object> criarConsultoria(@Valid @RequestBody ConsultoriaCreateDTO consultoriaCreateDTO) {
        try {
            Consultoria consultoria = consultoriaMapper.toEntity(consultoriaCreateDTO);
            Consultoria consultoriaGravada = consultoriaServico.gravarConsultoria(consultoria);
            ConsultoriaResponseDTO responseDTO = consultoriaMapper.toDTO(consultoriaGravada);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (ConsultoriaTipoJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("error", e.getMessage())
            );
        }
    }
    
    @GetMapping
    public ResponseEntity<Object> buscarConsultorias(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String tipoConsultoria) {
        
        try {
            List<Consultoria> consultorias = consultoriaServico.buscarPorIdOuTipo(id, tipoConsultoria);
            List<ConsultoriaResponseDTO> response = consultoriaMapper.toDTO(consultorias);
            return ResponseEntity.ok(response);
        } catch (ConsultoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", e.getMessage())
            );
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarConsultoria(
        @PathVariable Long id,
        @Valid @RequestBody ConsultoriaCreateDTO consultoriaCreateDTO) {
        
        try {
            Consultoria consultoria = consultoriaMapper.toEntity(consultoriaCreateDTO);
            Consultoria atualizada = consultoriaServico.alterarConsultoria(id, consultoria);
            return ResponseEntity.ok(consultoriaMapper.toDTO(atualizada));
            
        } catch (ConsultoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", e.getMessage())
            );
        } catch (ConsultoriaTipoJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of("error", e.getMessage())
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removerConsultoria(@PathVariable Long id) {
        try {
            consultoriaServico.apagarConsultoria(id);
            return ResponseEntity.ok(
                Map.of("message", "Consultoria removida com sucesso")
            );
        } catch (ConsultoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("error", e.getMessage())
            );
        }
    }
}