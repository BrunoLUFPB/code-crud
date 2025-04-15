package com.crudfla.crud_rest.servico;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudfla.crud_rest.excecao.ClienteCpfJaExistenteException;
import com.crudfla.crud_rest.excecao.ClienteNotFoundException;
import com.crudfla.crud_rest.modelo.Cliente;
import com.crudfla.crud_rest.repositorio.ClienteRepositorio;

@Service
public class ClienteServico {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
    public Cliente gravar(Cliente cliente) throws ClienteCpfJaExistenteException {
        if (clienteRepositorio.existsByCpf(cliente.getCpf())) {
            throw new ClienteCpfJaExistenteException("Já existe um cliente com o CPF: " + cliente.getCpf()); 
        } else {
            return clienteRepositorio.save(cliente);
        }
    }
	
    public List<Cliente> buscarPorIdOuNome(Long id, String nome) throws ClienteNotFoundException {
        List<Cliente> clientes = new ArrayList<>();
        
        if (id != null) {
            Cliente cliente = clienteRepositorio.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado."));
            
            if (nome != null && !nome.isBlank()) {
                if (!cliente.getNome().equalsIgnoreCase(nome)) {
                    throw new ClienteNotFoundException("Cliente não encontrado com os critérios fornecidos.");
                }
            }
            clientes.add(cliente);
            
        } else if (nome != null && !nome.isBlank()) {
            clientes = clienteRepositorio.findByNomeContainingIgnoreCase(nome);
        } else {
            clientes = clienteRepositorio.findAll();
        }
        
        if (clientes.isEmpty()) {
            throw new ClienteNotFoundException("Nenhum cliente encontrado.");
        }
        
        return clientes;
    }
	
    public Cliente alterarCliente(Long id, Cliente cliente) 
            throws ClienteNotFoundException, ClienteCpfJaExistenteException {
        
        Cliente clienteGravado = buscarPorIdOuNome(id, null).get(0);
        
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        
        boolean cpfAlterado = !Objects.equals(clienteGravado.getCpf(), cliente.getCpf());
        
        if (cpfAlterado) {
            boolean cpfExistente = clienteRepositorio.existsByCpfAndIdNot(cliente.getCpf(), id);
            if (cpfExistente) {
                throw new ClienteCpfJaExistenteException("CPF já está em uso por outro cliente: " + cliente.getCpf());
            }
        }
        
        clienteGravado.setNome(cliente.getNome());
        clienteGravado.setIdade(cliente.getIdade());
        clienteGravado.setCpf(cliente.getCpf()); 
        clienteGravado.setFidelidade(cliente.getFidelidade());
        clienteGravado.setCidade(cliente.getCidade());
        clienteGravado.setNumeroCelular(cliente.getNumeroCelular());
        
        return clienteRepositorio.save(clienteGravado);
    }


        public void apagarCliente(Long id) throws ClienteNotFoundException {

            Cliente cliente = buscarPorIdOuNome(id, null).get(0);
            clienteRepositorio.delete(cliente);
        }
    }

