package com.crudfla.crud_rest.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ConsultoriaExceptionHandler {

    // Tratamento para a exceção ConsultoriaNotFoundException
    @ExceptionHandler(ConsultoriaNotFoundException.class)
    public ResponseEntity<Object> handleConsultoriaNotFoundException(ConsultoriaNotFoundException ex) {
        // Retorna um erro 404 com a mensagem de exceção
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Tratamento para a exceção ConsultoriaNomeJaExistenteException
    @ExceptionHandler(ConsultoriaNomeJaExistenteException.class)
    public ResponseEntity<Object> handleConsultoriaNomeJaExistenteException(ConsultoriaNomeJaExistenteException ex) {
        // Retorna um erro 409 (Conflict) com a mensagem de exceção
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Tratamento para qualquer outra exceção genérica
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        // Retorna um erro 500 com uma mensagem genérica de erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + ex.getMessage());
    }
}
