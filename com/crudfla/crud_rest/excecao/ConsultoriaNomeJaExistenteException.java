package com.crudfla.crud_rest.excecao;

public class ConsultoriaNomeJaExistenteException extends RuntimeException {

    // Construtor padrão
    public ConsultoriaNomeJaExistenteException(String message) {
        super(message);
    }

    // Construtor com causa
    public ConsultoriaNomeJaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
