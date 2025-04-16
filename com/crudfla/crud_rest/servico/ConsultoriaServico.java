package com.crudfla.crud_rest.servico;

import com.crudfla.crud_rest.excecao.ConsultoriaNomeJaExistenteException;
import com.crudfla.crud_rest.excecao.ConsultoriaNotFoundException;
import com.crudfla.crud_rest.modelo.Consultoria;
import com.crudfla.crud_rest.repositorio.ConsultoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crudfla.crud_rest.modelo.Venda;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultoriaServico {

    @Autowired
    private ConsultoriaRepositorio consultoriaRepositorio;
    
 // Método para obter todas as vendas de uma consultoria
    public List<Venda> obterVendasDaConsultoria(Long consultoriaId) {
        return consultoriaRepositorio.findByVendasId(consultoriaId);
    }

    // Método para calcular o valor total das vendas de uma consultoria
    public Double calcularValorTotalVendas(Long consultoriaId) {
        List<Venda> vendas = obterVendasDaConsultoria(consultoriaId);
        return vendas.stream().mapToDouble(Venda::getValorTotal).sum();
    }

    // Método para criar uma nova consultoria
    public Consultoria gravar(Consultoria consultoria) throws ConsultoriaNomeJaExistenteException {
        if (consultoriaRepositorio.existsByNome(consultoria.getNome())) {
            throw new ConsultoriaNomeJaExistenteException("Já existe uma consultoria com o nome: " + consultoria.getNome());
        } else {
            return consultoriaRepositorio.save(consultoria);
        }
    }

    // Método para buscar consultorias por ID ou nome
    public List<Consultoria> buscarPorIdOuNome(Long id, String nome) throws ConsultoriaNotFoundException {
        List<Consultoria> consultorias;
        
        if (id != null) {
            consultorias = List.of(consultoriaRepositorio.findById(id).orElseThrow(() -> 
                new ConsultoriaNotFoundException("Consultoria não encontrada com o ID: " + id)));
        } else if (nome != null && !nome.isBlank()) {
            consultorias = List.of(consultoriaRepositorio.findByNome(nome));
            if (consultorias.isEmpty()) {
                throw new ConsultoriaNotFoundException("Consultoria não encontrada com o nome: " + nome);
            }
        } else {
            consultorias = consultoriaRepositorio.findAll();
        }

        return consultorias;
    }

    // Método para alterar os dados de uma consultoria existente
    public Consultoria alterarConsultoria(Long id, Consultoria consultoria) 
            throws ConsultoriaNotFoundException, ConsultoriaNomeJaExistenteException {
        
        Consultoria consultoriaGravada = buscarPorIdOuNome(id, null).get(0);

        boolean nomeAlterado = !Objects.equals(consultoriaGravada.getNome(), consultoria.getNome());
        
        if (nomeAlterado && consultoriaRepositorio.existsByNome(consultoria.getNome())) {
            throw new ConsultoriaNomeJaExistenteException("Já existe uma consultoria com o nome: " + consultoria.getNome());
        }

        consultoriaGravada.setNome(consultoria.getNome());
        consultoriaGravada.setPreco(consultoria.getPreco());
        consultoriaGravada.setEstoque(consultoria.getEstoque());
        consultoriaGravada.setConsultor_id(consultoria.getConsultor_id());
        consultoriaGravada.setCategoria(consultoria.getCategoria());
        consultoriaGravada.setDescricao(consultoria.getDescricao());

        return consultoriaRepositorio.save(consultoriaGravada);
    }

    // Método para deletar uma consultoria
    public void apagarConsultoria(Long id) throws ConsultoriaNotFoundException {
        Consultoria consultoria = buscarPorIdOuNome(id, null).get(0);
        consultoriaRepositorio.delete(consultoria);
    }
}
