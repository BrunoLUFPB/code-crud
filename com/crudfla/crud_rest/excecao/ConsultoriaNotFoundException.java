package com.crudfla.crud_rest.excecao;

public class ConsultoriaNotFoundException extends RuntimeException {

    // Construtor padrão
    public ConsultoriaNotFoundException(String message) {
        super(message);
    }

    // Construtor com causa
    public ConsultoriaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
