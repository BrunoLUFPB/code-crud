package com.crudfla.crud_rest.controle;

import java.util.List;

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
import org.springframework.web.bind.annotation.*;

import com.crudfla.crud_rest.dto.ConsultoriaCreateDTO;
import com.crudfla.crud_rest.dto.ConsultoriaResponseDTO;
import com.crudfla.crud_rest.dto.mapper.ConsultoriaMapper;
import com.crudfla.crud_rest.excecao.ConsultoriaNotFoundException;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.servico.ConsultoriaServico;
import com.crudfla.crud_rest.modelo.Venda;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultorias")
public class ConsultoriaControle {

    @Autowired
    private ConsultoriaServico consultoriaServico;
    
 // Endpoint para obter todas as vendas de uma consultoria
    @GetMapping("/{consultoriaId}/vendas")
    public ResponseEntity<List<Venda>> obterVendas(@PathVariable Long consultoriaId) {
        List<Venda> vendas = consultoriaServico.obterVendasDaConsultoria(consultoriaId);
        return ResponseEntity.ok(vendas);
    }

    // Endpoint para obter o valor total das vendas de uma consultoria
    @GetMapping("/{consultoriaId}/total-vendas")
    public ResponseEntity<Double> calcularTotalVendas(@PathVariable Long consultoriaId) {
        Double totalVendas = consultoriaServico.calcularValorTotalVendas(consultoriaId);
        return ResponseEntity.ok(totalVendas);
    }

    @Autowired
    private ConsultoriaMapper consultoriaMapper;

    // POST - Create new Consultoria
    @PostMapping
    public ResponseEntity<Object> salvar(@Valid @RequestBody ConsultoriaCreateDTO consultoriaCreateDTO) {
        try {
            Consultoria consultoria = consultoriaMapper.toEntity(consultoriaCreateDTO);
            Consultoria consultoriaGravada = consultoriaServico.gravar(consultoria);
            ConsultoriaResponseDTO consultoriaResponseDTO = consultoriaMapper.toDTO(consultoriaGravada);
            return ResponseEntity.status(HttpStatus.CREATED).body(consultoriaResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET - Search Consultoria by ID or Name
    @GetMapping
    public ResponseEntity<Object> buscarConsultoria(
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String nome) throws ConsultoriaNotFoundException {

        List<Consultoria> consultorias = consultoriaServico.buscarPorIdOuNome(id, nome);
        List<ConsultoriaResponseDTO> consultoriasResponseDTO = consultoriaMapper.toDTO(consultorias);
        return ResponseEntity.ok(consultoriasResponseDTO);
    }

    // PUT - Update an existing Consultoria by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> alterar(
        @PathVariable Long id,
        @RequestBody ConsultoriaCreateDTO consultoriaCreateDTO) {

        try {
            Consultoria consultoria = consultoriaMapper.toEntity(consultoriaCreateDTO);
            Consultoria consultoriaGravada = consultoriaServico.alterarConsultoria(id, consultoria);
            ConsultoriaResponseDTO responseDTO = consultoriaMapper.toDTO(consultoriaGravada);
            return ResponseEntity.ok(responseDTO);
        } catch (ConsultoriaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DELETE - Delete a Consultoria by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> apagar(@PathVariable(value = "id") Long id) throws ConsultoriaNotFoundException {
        consultoriaServico.apagarConsultoria(id);
        return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
    }
}
