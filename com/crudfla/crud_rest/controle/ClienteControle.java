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

import com.crudfla.crud_rest.dto.ClienteCreateDTO;
import com.crudfla.crud_rest.dto.ClienteResponseDTO;
import com.crudfla.crud_rest.dto.mapper.ClienteMapper;
import com.crudfla.crud_rest.excecao.ClienteCpfJaExistenteException;
import com.crudfla.crud_rest.excecao.ClienteNotFoundException;
import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.servico.ClienteServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {
	
	@Autowired
	private ClienteServico clienteServico;
	
	@Autowired
	private ClienteMapper clienteMapper;
	
	@PostMapping
	public ResponseEntity<Object> salvar(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
	    try {
	        Cliente cliente = clienteMapper.toEntity(clienteCreateDTO);
	        Cliente clienteGravado = clienteServico.gravar(cliente);
	        ClienteResponseDTO clienteResponseDTO = clienteMapper.toDTO(clienteGravado);
	        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);
	    } catch (ClienteCpfJaExistenteException e) { 
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }
	}
	
	@GetMapping
	public ResponseEntity<Object> buscarCliente(
	    @RequestParam(required = false) Long id,
	    @RequestParam(required = false) String nome) throws ClienteNotFoundException {

	    
	        List<Cliente> clientes = clienteServico.buscarPorIdOuNome(id, nome);
	        List<ClienteResponseDTO> clientesResponseDTO = clienteMapper.toDTO(clientes);
	        return ResponseEntity.ok(clientesResponseDTO);
	    } 
	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> alterar(
	    @PathVariable Long id, 
	    @RequestBody ClienteCreateDTO clienteCreateDTO) {
	    
	    try {
	        Cliente cliente = clienteMapper.toEntity(clienteCreateDTO);
	        Cliente clienteGravado = clienteServico.alterarCliente(id, cliente);
	        ClienteResponseDTO responseDTO = clienteMapper.toDTO(clienteGravado);
	        return ResponseEntity.ok(responseDTO);
	        
	    } catch (ClienteNotFoundException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	        
	    } catch (ClienteCpfJaExistenteException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	        
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> apagar (@PathVariable(value = "id") Long id) throws ClienteNotFoundException {
		
			clienteServico.apagarCliente(id);
			return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso.");
		} 
	}
	

	
	
	
	
	


